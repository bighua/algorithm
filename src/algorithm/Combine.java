package algorithm;

public class Combine {

	private static void combine(int[] a,int n, int m, int[] b, int M) {
		for (int i = n; i >= m; i--) {
			b[m - 1] = i -1;
			if (m > 1) {
				combine(a, i - 1, m - 1, b, M);
			} else {
				for (int j = M - 1;j >=0; j--) {
					System.out.print(a[b[j]] + "-");
				}
				System.out.println();
			}
		}
	}
	
	private static void combine(int[] a, int n,int m) {
		int[] order = new int[m+1];
		for (int i = 0; i<=m; i++) {
			order[i] = i -1;
		}
		int count = 0;
		int k = m;
		boolean flg = true;
		while (order[0] == -1) {
			if (flg) {
				for (int i = 1; i <=m ; i++) {
					System.out.print(a[order[i]] + "-");
				}
				System.out.println();
				count++;
				flg = false;
			}
			order[k]++;
			if (order[k] == n) {
				order[k--] = 0;
				continue;
			}
			if (k < m) {
				order[++k] = order[k-1];
				continue;
			}
			if (k == m) {
				flg = true;
			}
		}
		System.out.println(count);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[]{1,2,3,4,5};
		int[] b = new int[3];
//		combine(a, 5, 3, b, 3);
		combine(a, 5, 3);
	}

}
