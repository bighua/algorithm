package algorithm.dp;

/**
 * 最优二叉搜索树
 * @author zhuxh
 *
 */
public class BST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		double[] p = {0d, 0.04, 0.06, 0.08, 0.02,0.10,0.12,0.14};
//		double[] q = {0.06,0.06,0.06,0.06,0.05,0.05,0.05,0.05};
		double[] p = {0.00,0.15,0.10,0.05,0.10,0.20};
		double[] q = {0.05,0.10,0.05,0.05,0.05,0.10};
		
		optimal_bst(p, q, 5);
		
	}
	
	private static void optimal_bst(double[] p,double[] q,int n) {
		double[][] e = new double[n+2][n+1];
		double[][] w = new double[n+2][n+1];
		int[][] root = new int[n+1][n+1];
		for (int i = 1; i <= n+1;i++) {
			e[i][i-1] = q[i-1];
			w[i][i-1] = q[i-1];
		}
		for (int l = 1;l <= n;l++) {
			for (int i = 1; i <= n-l+1; i++) {
				int j = i + l - 1;
				e[i][j] = Double.MAX_VALUE;
				w[i][j] = w[i][j-1] + p[j] + q[j];
				double tmp = 0d;
				for (int r = i; r <= j; r++) {
					tmp = e[i][r-1] + e[r+1][j] + w[i][j];
					if (tmp < e[i][j]) {
						e[i][j] = tmp;
						root[i][j] = r;
					}
				}
			}
		}
		System.out.println(e[1][n]);
		System.out.println("k" + root[1][n] + " is the root");
		printTree(root, 1, n);
	}
	
	private static void printTree(int[][] root , int i, int j) {
		if (j == i - 1) {
			return;
		}
		int rootV = root[i][j];
		if (rootV == i) {
			System.out.println("d" + (i - 1) + "is the left child of " + "k" + rootV);
		} else {
			System.out.println("k" + root[i][rootV - 1] + "is the left child of " + "k" + rootV);
		}
		printTree(root, i , rootV -1);
		if (rootV == j) {
			System.out.println("d" + j + "is the right child of " + "k" + rootV);
		} else {
			System.out.println("k" + root[rootV + 1][j] + "is the right child of " + "k" + rootV);
		}
		printTree(root, rootV + 1, j);
	}
}
