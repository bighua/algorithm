package algorithm.dp;

import java.util.Random;


/**
 * 最长公共子序列——动态规划（dp）
 * 给定两个序列X=X1X2...Xn,Y=Y1Y2...Yn，求X和Y长度最长公共子序列（在X，Y中子序列可不连续）
 * @author zhuxh
 *
 */
public class LCS_length implements Al_dp {

	@Override
	public void printDp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		lcs_length(new String[]{"a", "b", "c", "b", "d", "a", "b"}, new String[]{"b", "d", "c", "a", "b", "a"});
//		System.out.println();
//		lcs_length("1,0,0,1,0,1,0,1".split(","), "0,1,0,1,1,0,1,1,0".split(","));
//		lcs_length1(new String[]{"a", "b", "c", "b", "d", "a", "b"}, new String[]{"b", "d", "c", "a", "b", "a"});
		Random r = new Random();
		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i]=r.nextInt(100);
			System.out.print(a[i] + "-");
		}
		System.out.println();
//		LongestIncrease(a);
	}
	
	private static void lcs_length(String[] x, String[] y) {
		int m = x.length;
		int n = y.length;
		int[][] c = new int[m+1][n+1];
		String[][] b = new String[m+1][n+1];
		for (int i = 0; i <= m; i++) {
			c[i][0] = 0;
		}
		for (int j = 1; j <= n; j++) {
			c[0][j] = 0;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <=n; j++) {
				if (x[i - 1].equals(y[j - 1])) {
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = "LU";
				} else if (c[i-1][j] >= c[i][j-1]) {
					c[i][j] = c[i-1][j];
					b[i][j] = "U";
				} else {
					c[i][j] = c[i][j-1];
					b[i][j] = "L";
				}
			}
		}
		print_lcs(b, x, m, n);
		System.out.println();
		print_lcs1(c, x, y, m, n);
	}
	
	private static void lcs_length1(String[] x, String[] y) {
		int m = x.length;
		int n = y.length;
		int l = 0;
		int s = 0;
		if (m > n) {
			l = m;
			s = n;
		} else {
			l = n;
			s = m;
		}
		int[] c = new int[s + 1];
		for (int i = 1; i <= l; i++) {
			for (int j = 1; j <=s; j++) {
				if (x[i - 1].equals(y[j - 1])) {
					c[j] = c[j-1] + 1;
				} else if (c[j] < c[j-1]) {
					c[j] = c[j-1];
				} else {
				}
			}
		}
		for (int i : c) {
			System.out.print(i + "-");
		}
	}
	
	private static void print_lcs(String[][] c, String[] x, int m, int n) {
		if (m == 0 || n == 0) return;
		if ("LU".equals(c[m][n])) {
			print_lcs(c, x, m - 1, n - 1);
			System.out.print((x[m-1]));
		} else if ("U".equals(c[m][n])) {
			print_lcs(c, x, m - 1, n);
		} else {
			print_lcs(c, x, m, n - 1);
		}
	}
	
	private static void print_lcs1(int[][] c,String[] x, String[] y, int m, int n) {
		if (m == 0 || n == 0) return;
		if (x[m - 1].equals(y[n - 1])) {
			print_lcs1(c, x, y, m - 1, n - 1);
			System.out.print(x[m-1]);
		} else if (c[m][n] == c[m-1][n]) {
			print_lcs1(c, x, y, m - 1, n);
		} else {
			print_lcs1(c, x, y, m, n - 1);
		}
	}
}
