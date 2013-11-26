package algorithm.divide2ndConquer;

import java.util.Arrays;
import java.util.Stack;

/**
 * 分治策略 —— 寻找和最大子数组
 * 时间复杂度 T(n)=nlgn
 * 1.递归 findMaximumSubArray
 * 2.线性 findMaxSubWithoutRe
 * 
 * @author zhuxh
 *
 */
public class FindMaximumSubArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, 22, 15, -4, 7};
		int[] result = findMaximumSubArray(a, 0, a.length - 1);
		System.out.println("the max subarray is from " + result[1] + " to " + result[2] + ", and the total is " + result[0]);
		findMaxSubWithoutRe(a);
		largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
		largestRectangleArea2(new int[]{4, 3, 5, 6, 4, 3});
	}
	
	private static int[] findMaximumSubArray(int[] a, int start, int end) {
		int[] result = new int[3];
		if (start == end) {
			result[0] = a[start];
			result[1] = start;
			result[2] = end;
		} else {
			int mid = (start + end) / 2;
			int[] left = findMaximumSubArray(a, start, mid);
			int[] right = findMaximumSubArray(a, mid + 1, end);
			int[] center = findMaxCrossSubArray(a, start, mid, end);
			if (left[0] >= right[0] && left[0] >= center[0]) {
				result = left;
			} else if (right[0] >= left[0] && right[0] >= center[0]) {
				result = right;
			} else {
				result = center;
			}
		}
		return result;
	}
	
	private static int[] findMaxCrossSubArray(int[] a, int start, int mid, int end) {
		int[] result = new int[3];
		int sum = Integer.MIN_VALUE;
		int tmpSum = 0;
		int left = mid;
		int right = mid;
		for (int i = mid; i >= start; i--) {
			tmpSum += a[i];
			if (tmpSum > sum) {
				sum = tmpSum;
				left = i;
			}
		}
		tmpSum = sum;
		for (int j = mid + 1; j <= end; j++) {
			tmpSum += a[j];
			if (tmpSum > sum) {
				sum = tmpSum;
				right = j;
			}
		}
		result[0] = sum;
		result[1] = left;
		result[2] = right;
		return result;
	}
	
	private static void findMaxSubWithoutRe(int[] a) {
		int sum = Integer.MIN_VALUE;
		int tmpSum = 0;
		int left = 0;
		int right = 0;
		for (int i = 0; i < a.length; i++) {
			if (i == 0) {
				sum = a[i];
			} else {
				tmpSum = sum;
				for (int j = right + 1; j <= i; j++) {
					tmpSum += a[j];
				}
				if (tmpSum > sum) {
					sum = tmpSum;
					right = i;
				} else {
					for (int j = left; j <= i; j++) {
						tmpSum -= a[j];
						if (tmpSum > sum) {
							sum = tmpSum;
							left = j + 1;
							right = i;
						}
					}
				}
			}
			
		}
		System.out.println("the max subarray is from " + left + " to " + right + ", and the total is " + sum);
	}
	
	/**
	 * 给定直方图，每一小块的height由N个非负整数所确定，每一小块的width都为1，请找出直方图中面积最大的矩形。
	 * 如下图所示，直方图中每一块的宽度都是1，每一块给定的高度分别是[2,1,5,6,2,3]：
	 * 那么上述直方图中，面积最大的矩形便是下图所示的阴影部分的面积，面积= 10单位。
	 * 
	 *    ^
	 *   ^^
	 *   ^^
	 *   ^^ ^
	 * ^ ^^^^
	 * ^^^^^^
	 * @param p
	 */
	private static void largestRectangleArea(int[] p) {
		int maxArea = 0;
		int left = 0;
		int right = 0;
		for (int i = 0; i < p.length; i++) {
			int tmpArea = p[i];
			int tmpLowest = p[i];
			for (int j = i; j >= 0; j--) {
				if (p[j] < tmpLowest) {
					tmpLowest = p[j];
				}
				tmpArea = tmpLowest * (i - j + 1);
				if (tmpArea > maxArea) {
					left = j;
					right = i;
					maxArea = tmpArea;
				}
			}
		}
		System.out.println("The biggest block area is " + left + "-" + right + ":" + maxArea);
	}
	
	public static int largestRectangleArea2(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while(i < h.length){
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i++);
            }else {
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        System.out.println(maxArea);
        return maxArea;
    }

}
