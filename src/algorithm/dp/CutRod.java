package algorithm.dp;


/**
 * 动态规划 —— 钢条切割
 * Dynamic programming
 * 两种动态规划方法求得切割钢条的最优化解
 * 1.带备忘录的自顶向下法 memoizedCutRod
 * 2.自底向上 bottomUpCutRod
 * 
 * @author zhuxh
 *
 */
public class CutRod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
		memoizedCutRod(p, 10);
		bottomUpCutRod(p, 10);
	}
	
	private static void memoizedCutRod(int[] p, int n) {
		int[] r = new int[n + 1];
		int[] solution = new int[n + 1];
		for (int i = 0; i < r.length; i++) {
			r[i] = Integer.MIN_VALUE;
		}
		System.out.println(memoizedCutRodAux(p, n, r, solution));
		for (int k : r) {
			System.out.print(k + "-");
		}
		System.out.println();
		for (int k : solution) {
			System.out.print(k + "-");
		}
	}
	
	private static int memoizedCutRodAux(int[] p, int n,int[] r, int[] solution) {
		if (r[n] >= 0) {
			return r[n];
		}
		int q = Integer.MIN_VALUE;
		if (n == 0) {
			q = 0;
			solution[n] = 0;
		} else {
			int tmp = Integer.MIN_VALUE;
			for (int i = 1; i <= n; i++) {
				tmp = p[i] + memoizedCutRodAux(p, n - i, r, solution);
				if (tmp > q) {
					q = tmp;
					solution[n] = i;
				}
			}
		}
		r[n] = q;
		return q;
	}
	
	private static void bottomUpCutRod(int[] p, int n) {
		int[] r = new int[n + 1];
		int[] s = new int[n + 1];
		r[0] = 0;
		s[0] = 0;
		int tmp = Integer.MIN_VALUE;
		int q = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				
				tmp = p[j] + p[i - j];
				if (tmp > q) {
					q = tmp;
					s[i] = j;
				}
			}
			r[i] = q;
		}
		for (int k : r) {
			System.out.print(k + "-");
		}
		System.out.println();
		for (int k : s) {
			System.out.print(k + "-");
		}
	}
}
