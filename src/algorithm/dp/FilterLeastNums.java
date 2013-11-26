package algorithm.dp;

public class FilterLeastNums {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = new int[]{1,2,3,4,9,5,7,8,6,5,4};
		filter(a, a.length);
	}
	
	public static void filter(int[] a, int n) {
		int b[] = new int[n];
		int c[] = new int[n];
		int inc[] = new int[n];
		
		int low, high, mid;
		int max = 0;
		for (int i = 0; i < n; i++) inc[i] = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			low = 0;
			high = i;
			while (low < high)  {
				mid = low + (high - low) / 2;
				if (inc[mid] < a[i]) {
					low = mid + 1;
				} else {
					high = mid;
				}
			}
			b[i] = low + 1;
			inc[low] = a[i];
		}

		for (int i = 0; i < n; i++) inc[i] = Integer.MAX_VALUE;
		for (int i = n-1; i >= 0; i--) {
			low = 0;
			high = i;
			while (low < high)  {
				mid = low + (high - low) / 2;
				if (inc[mid] < a[i]) {
					low = mid + 1;
				} else {
					high = mid;
				}
			}
			c[i] = low + 1;
			inc[low] = a[i];
		}
		
		int point = 0;
		for (int i = 0; i < n;i++) {
			if (b[i] + c[i] > max) {
				max = b[i] + c[i];
				point = i;
			}
		}
		System.out.println(point);
		System.out.println(n - max + 1);
	}

}
