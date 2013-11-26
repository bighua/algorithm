package algorithm.sort;

import java.util.Vector;

public class LinearSort implements Sort {

	@Override
	public void sort() {
		

	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinearSort countingSort = new LinearSort();
//		int[] a = new int[]{2,5,3,0,2,3,0,3};
//		int[] b = countingSort.countingSort(a, 5);
//		for (int i = 0; i < b.length; i++) {
//			System.out.print(b[i] + " ");
//		}
//		System.out.println();
//		int[] a1 = new int[]{1,11,111,3,43,35,234};
//		a1 = countingSort.radixSort(a1, 3);
//		for (int i = 0; i < a1.length; i++) {
//			System.out.print(a1[i] + " ");
//		}
//		System.out.println();
		double[] d = countingSort.bucketSort(new double[]{0.78,0.17,0.39,0.26,0.72,0.94,0.21,0.12,0.23,0.68});
		for (int i = 0; i < d.length; i++) {
			System.out.print(d[i] + " ");
		}
	}
	
	/**
	 * 计数排序
//	 * 假设n个输入元素中每一个都是0到k区间内的一个整数，其中k为某个整数，当k=O(n)时，排序的运行时间是渐进为n
	 * @param a
	 * @param k
	 * @return
	 */
	public int[] countingSort(int[] a, int k) {
		int[] b = new int[a.length];
		int[] c = new int[k+1];
		
		for (int i = 0; i < a.length;i++) {
			c[a[i]] = c[a[i]] + 1;
		}
		
		for (int i = 1; i < c.length; i++) {
			c[i] = c[i - 1] + c[i];
		}
		
		for (int i = a.length - 1; i >=0; i--) {
			b[c[a[i]]-1] = a[i];
			c[a[i]] = c[a[i]] - 1;
		}
		return b;
	}
	
	/**
	 * 基数排序
	 * 给定n个d位数，其中每一个数位都有k个可能的取值。如果radixSort使用的稳定排序方法耗时⊙(n+k)
	 * 那么它就能在⊙(d(n+k))时间内将这些数排好序
	 * @param a
	 * @param d
	 * @return
	 */
	public int[] radixSort(int[] a, int d) {
		int k = 1;
		for (int i = 0; i < d;i++) {
			int[] digits = new int[a.length];
			k *= 10;
			for (int j = 0; j < a.length; j++) {
				digits[j] = (a[j] % k) / (k / 10);
			}
			a = counting4radix(digits, 9, a);
		}
		return a;
	}
	
	private int[] counting4radix(int[] a, int k, int[] origin) {
		int[] b = new int[a.length];
		int[] c = new int[k+1];
		
		for (int i = 0; i < a.length;i++) {
			c[a[i]] = c[a[i]] + 1;
		}
		
		for (int i = 1; i < c.length; i++) {
			c[i] = c[i - 1] + c[i];
		}
		
		for (int i = a.length - 1; i >=0; i--) {
			b[c[a[i]]-1] = origin[i];
			c[a[i]] = c[a[i]] - 1;
		}
		return b;
	}
	
	/**
	 * 桶排序
	 * 假设输入是由一个随机过程产生，该过程将元素均匀，独立的分布在[0,1)区间上
	 * 桶排序将[0,1)区间划分为n个相同大小的子区间（桶），将n个输入数分别放入各个桶中，先对桶中数进行排序，然后遍历每个桶
	 * @param a
	 * @return
	 */
	public double[] bucketSort(double[] a) {
		double[] b = new double[a.length];
		Vector[] buckets = new Vector[10];
		for (int i = 0; i < a.length; i++) {
			int bucket = (int)(a[i] * 10);
			if (buckets[bucket] == null) {
				buckets[bucket] = new Vector();
			}
			buckets[bucket].add(a[i]);
		}
		// concatenate all buckets in order
		int k = 0;
		for (int i = 0; i < buckets.length; i++) {
			Vector v = buckets[i];
			if (v != null && v.size() != 0) {
				
				// sort every bucket with insertion sort
				insertSort4bucket(v);
				for (int j = 0; j < v.size(); j++) {
					b[k++] = (Double)v.get(j);
				}
			} 
		}
		return b;
	}
	
	/**
	 * 桶内元素插入排序
	 * @param v
	 */
	private void insertSort4bucket(Vector v) {
		for (int i = 1; i < v.size(); i++) {
			int j = i - 1;
			double key = (Double)v.get(i);
			while (j >= 0 && key < (Double)v.get(j)) {
				v.set(j + 1, v.get(j));
				j--;
			}
			v.set(j + 1, key);
		}
	}

}
