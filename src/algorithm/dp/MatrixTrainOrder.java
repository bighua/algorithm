package algorithm.dp;


/**
 * 矩阵链乘法问题——动态规划（dp）
 * 求完全括号化方案，使得计算乘积A1A2...An所需标量乘法次数最少
 * @author zhuxh
 *
 */
public class MatrixTrainOrder implements Al_dp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] p = new int[]{30, 35, 15 , 5, 10, 20, 25};
		matrixTrainOrder(p);
		maxtrixChainRec(p);
	}
	
	private static void matrixTrainOrder(int[] p) {
		int n = p.length - 1;
		// m[1..n,1..n],s[1..n-1][2..n]
		int[][] m = new int[n + 1][n + 1];
		for (int i = 0; i < m.length; i++) {
			m[i][i] = 0;
		}
		int[][] s = new int[n][n + 1];
		for (int l = 2; l <= n; l++) {
			int q = 0;
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		System.out.println(m[1][n]);
		System.out.println(printOptimalParens(s, 1, n));
	}
	
	private static void maxtrixChainRec(int[] p) {
		int n = p.length - 1;
		int[][] s = new int[n][n + 1];
		System.out.println(maxtrixChainMultiply(p, s, 1, n));
		System.out.println(printOptimalParens(s, 1, n));
	}
	
	private static int maxtrixChainMultiply(int[] p, int[][] s, int i, int j) {
		if (i == j) {
			return 0;
		} else {
			int q = Integer.MAX_VALUE;
			for (int k = i; k < j; k++) {
				int tmp = maxtrixChainMultiply(p, s, i, k) + maxtrixChainMultiply(p, s, k + 1, j) + p[i - 1] * p[k] * p[j];
				if (tmp < q) {
					q = tmp;
					s[i][j] = k;
				}
			}
			return q;
		}
	}
	
	private static String printOptimalParens(int[][] s, int i, int j) {
		String result = "";
		if (i == j) {
			result = "A" + i;
		} else {
			result = "(" + 
					printOptimalParens(s, i, s[i][j]) +
					printOptimalParens(s, s[i][j] + 1, j) + ")";
		}
		return result;
	}

	@Override
	public void printDp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
