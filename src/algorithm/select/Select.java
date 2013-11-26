package algorithm.select;


public class Select {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[] a = new int[]{5,2,4,7,1,3,2,6};
//		Select s = new Select();
//		System.out.println(s.randomizedSelect(a, 0, 7, 5));
		int[] a = new int[]{-190583336,387430802,263667328,510431117,331131471,-918010816,704838426,-677210050,360848656,742630124,15703242,395195320,817938673,-623093705,283033106,-239666201,-265920704,773391770,-760847696,607682585,-563913441,-881233152,-984709642,-174073208,-128383092,-171400737,-952079810,238331198,-633431229,-201310928,245462912,982384290,-770450530,626827839,-794748659,-255078696,-992767030,-981566859,-711588051,-616504163,482100591,-687955033,109864672,-969740858,-885886557,-660860418,441019307,121290346,992125167,-711203919,196828974,485410708,291894741,-617566705,852834033,-442552631,-385062138,-47017429,-403793521,470657775,82247607,-364993466,-681193175,-819342255,-747416039};
		int[] b = new int[]{392825514,-836017660,138081281,329808740,-280324655,803138486,854876869,389984726,-244985191,-876072057,-821206239,34507921,-568279731,451356133,-27546827,756251594,-489648498,-27639800,733283350,-24308299,98957415,-589892336,-312655982,791183257,-531958490,88038350,-426768961,679976077,329175384,364827141,-447799883,-252798101,198546988,-968033288,-958732516,256652618,45755534,287753479,-15183788,311761586,-876636063,496818060,-380097510,800906365,-586781968,-980669346,50038291,605726869,987308354,819121868,-816937340,-797391481,-858699871,99190694,194851443,-219504107,-620908161,-52751084,810633507,-714285738,733900697,-783139011,-87776034,514854263,198242697};
		//		System.out.println(getMid(a,0,a.length - 1,b,0,b.length - 1, a.length));
		System.out.println(bestDistance(a, b));
	}

	
	/**
	 * 从给定数组a中找到第i小的那个数
	 * @param a
	 * @param low
	 * @param high
	 * @param i
	 * @return
	 */
	public long randomizedSelect(long[] a, int low, int high, int i) {
		if (low == high) return a[low];
		int pivot = randomizedPartition(a, low, high);
		int k = pivot - low + 1;
		if (i == k) 
			return a[pivot];
		else if (i < k)
			return randomizedSelect(a, low, pivot - 1, i);
		else 
			return randomizedSelect(a, pivot + 1, high, i - k);
	}
	

	/**
	 * 随机取得pivot,单头重排
	 * @param low
	 * @param high
	 * @return
	 */
	public int randomizedPartition(long[] a,int low, int high) {
		int pivot = low + (int)(Math.random() * (high - low));
		long tmp = a[high];
		a[high] = a[pivot];
		a[pivot] = tmp;
		
		return partition1way(a, low, high);
	}
	
	private int partition1way(long[] a,int low, int high) {
		long tmp = 0;
		int l = low - 1;
		int j = low;
		while (j < high) {
			if (a[j] <= a[high]) {
				l = l + 1;
				tmp = a[l];
				a[l] = a[j];
				a[j] = tmp;
			}
			j++;
		}
		tmp = a[l+1];
		a[l+1] = a[high];
		a[high] = tmp;
		return l+1;
	}
	
	/**
	 * 给定包含n个有序元素的两个数组a，b，找出两个数组a，b中所有2n个元素的中位数
	 * @param a
	 * @param as
	 * @param ae
	 * @param b
	 * @param bs
	 * @param be
	 * @param n
	 * @return
	 */
	public static int getMid(int[] a, int as, int ae, int[] b, int bs, int be, int n) {
		if (a[as] >= b[be]) {
			System.out.println(n);
			if (be - bs + 1 == n)
				return b[be];
			else 
				return a[as - 1] > b[be - 1] ? a[as - 1] : b[be - 1];
		}
		if (b[bs] >= a[ae]) {
			System.out.println(n);
			if (ae - as + 1 == n)
				return a[ae];
			else 
				return a[ae - 1] > b[bs - 1] ? a[ae - 1] : b[bs - 1];
		}
		int am = (ae + as) / 2;
		int bm = (be + bs) / 2;
		if (a[am] > b[bm]) {
			return getMid(a, as, am, b, bm + 1, be, (n - (bm - bs + 1)));
		} else {
			return getMid(a, am + 1, ae, b, bs, bm, (n - (am - as + 1)));
		}
	}
	
	/**
	 * 邮局位置问题
	 * 给定权重分别为W1,W2,...,Wn的n个点P1,P2,..Pn,我们希望找到一个点P(不一定是输入点中的一个)，使得∑Wid(P,Pi)最小，这里d(a,b)表示a与b之间的距离
	 * 一维邮局位置问题，带权中位数求解
	 * 二维邮局位置问题，其中的点是(x,y)二维坐标形式，点a=(x1,y1)与b=(x2,y2)之间的距离是曼哈顿距离，即d(a,b)=|x1-x2|+|y1-y2|
	 * 
	 * 以下解决曼哈顿距离问题
	 * @param x
	 * @param y
	 * @return
	 */
	public static double manhattanDistance(long[] x, long[] y) {
		Select xs = new Select();
		Select ys = new Select();
		
		long xm = xs.randomizedSelect(x, 0, x.length - 1, (x.length + 1)/2);
		long ym = ys.randomizedSelect(y, 0, y.length - 1, (y.length + 1)/2);
		
		double sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum += Math.abs(x[i] - xm);
			sum += Math.abs(y[i] - ym);
		}
		return sum;
	}
	
	/**
	 * 要建立一个信号基站服务n个村庄，这n个村庄用平面上的n个点表示。
	 * 假设基站建立的位置在(X,Y)，则它对某个村庄(x,y)的距离为max{|X – x|, |Y – y|}， 其中| |表示绝对值，我们的目标是让所有村庄到信号基站的距离和最小。 
	 * 基站可以建立在任何实数坐标位置上，也可以与某村庄重合。
	 * 
	 * 输入： 给定每个村庄的位置x[],y[]，x,y都是整数，满足：-1000000000 < x,y < 1000000000 村庄个数大于1，小于101。
	 * 输出： 所有村庄到信号基站的距离和的最小值。
	 * 
	 * max{|X – x|, |Y – y|} = (|(X-Y)-(x-y)|+|(X+Y)-(x+y)|)/2
	 * @param x
	 * @param y
	 * @return
	 */
	public static double bestDistance(int[] x, int[] y) {
		// 转换成曼哈顿距离
		long a[] = new long[x.length];
		long b[] = new long[x.length];
		for (int i = 0; i < x.length; i++) {
			a[i] = x[i] - y[i];
			b[i] = x[i] + y[i];
		}
		
		return manhattanDistance(a, b) / 2;
	}
}
