package algorithm.sort;

/**
 * Young式矩阵
 * 在一个m * n的Young式矩阵中，每一行的数据都从左到右排序，每一列的数据都从上到小排序
 * ∞表示那些不存在的值 
 * 1 | 2 | 8  | 9
 * 2 | 4 | 9  | 12
 * 4 | 7 | 10 | 13
 * 6 | 8 | 11 | 15
 * 
 * @author zhuxh
 *
 */
public class YoungMatrix {

	private int[][] s;
	private int last_r = 0;
	private int last_c = 0;
	private int row = 0;
	private int col = 0;
	
	public YoungMatrix(int[][] s) {
		this.s = s;
		row = last_r = s.length - 1;
		col = last_c = s[0].length - 1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] s = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
		YoungMatrix ym = new YoungMatrix(s);
//		for (int i = 0; i < s.length; i++) {
//			for (int j = 0; j < s[0].length; j++) {
//				System.out.print(ym.extract_min() + " ");
//			}
//		}
		ym.findKey(9);
	}
	
	public int extract_min() {
		if (s[0][0] == Integer.MAX_VALUE) {
			return Integer.MIN_VALUE;
		}
		int min = s[0][0];
		s[0][0] = s[last_r][last_c];
		s[last_r][last_c] = Integer.MAX_VALUE;
		min_modify(0, 0);
		return min;
	}
	
	public boolean findKey(int key) {
		int col = this.col;
		int row = 0;
		boolean exist = false; 
		while (col >= 0 && row <= this.row) {
			if (key < s[row][col]) {
				col--;
			} else if (key > s[row][col]) {
				row++;
			} else {
				exist = true;
				break;
			}
		}
		if (exist) System.out.println(key + " is at the (" + row + ", " + col + ")");
		else System.out.println(key + " is not in the matrix");
		return exist;
	}
	
	private void min_modify(int r, int c) {
		
		int tmp = s[r][c];
		int least_r = r;
		int least_c = c;
		int least = tmp;
		if (c + 1 <= col && least > s[r][c+1]) {
			least = s[r][c+1];
			least_c = c + 1;
		}
		if (r + 1 <= row && least > s[r+ 1][c]) {
			least = s[r+1][c];
			least_c = c;
			least_r = r + 1;
		}
		if (least_r != r || least_c != c) {
			s[r][c] = least;
			s[least_r][least_c] = tmp;
			min_modify(least_r, least_c);
		} else {
			last_r = least_r;
			last_c = least_c;
		}
	}

}
