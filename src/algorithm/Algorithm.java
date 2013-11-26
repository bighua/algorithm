package algorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Algorithm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date s = new Date();
//		LuckyNum();
//		getPrime();
//		System.out.println(longestValidParentheses("(()(()()"));
//		System.out.println(can(7, 5, 3));
//		System.out.println(perfect("zfghzdadgaaa"));
//		for (int k : a) {
//			System.out.println(k);
//		}
//		System.out.println(can(1234567, 7654321, 9999999));
//		List<int[]> num = new ArrayList<int[]>();
//		num.add(new int[]{3,1});
//		num.add(new int[]{3,1});
//		num.add(new int[]{8,1});
//		num.add(new int[]{8,1});
//		List<String> stack = new ArrayList<String>();
//		System.out.println(get24(num, new int[]{24,1}, stack));
////		System.out.println(can(117, 7, 55));
////		System.out.println(can(7, 2, 17));
//		for (String str : stack) {
//			System.out.print(str);
//		}
//		System.out.println();
//		System.out.println((new Date()).getTime() - s.getTime());
//		huiwen(45, new int[]{14,15,16});
//		huiwen(5, new int[]{2,3});
//		System.out.println(pow(7, 280, 561));
		int[] a = new int[]{1 ,29 ,3 ,4 ,15 ,6 ,19 ,72 ,9 ,10 ,11 ,12 ,13 ,40
				,48 ,50 ,17 ,18 ,14 ,67 ,96 ,22 ,23 ,20 ,25 ,26 ,27 ,7 ,46 ,85 
				,31 ,79 ,33 ,38 ,16 ,36 ,37 ,54 ,51 ,8 ,41 ,42 ,43 ,44 ,2 ,75 
				,47 ,5 ,100 ,24 ,98 ,52 ,53 ,35 ,55 ,56 ,57 ,58 ,59 ,60 ,61 
				,62 ,63 ,64 ,65 ,66 ,28 ,68 ,69 ,70 ,71 ,30 ,21 ,74 ,78 ,76 
				,77 ,34 ,39 ,80 ,81 ,82 ,83 ,84 ,45 ,86 ,87 ,73 ,89 ,90 ,91 
				,92 ,93 ,94 ,95 ,88 ,97 ,49 ,99 ,32 };
//		int[] a = new int[]{1 ,8 ,3 ,14 ,2 ,6 ,5 ,7 ,9 ,10 ,11 ,12 ,13 ,4};
//		for (int i = 100; i > 0; i--) {
//			a[100 - i] = i;
//		}
		int[] b = new int[]{2,3,2,3};
		System.out.println(run1(b));
//		System.out.println(run(new int[]{2,3,1}));
	}

	private static int reverse(int x) {
		int result = 0;
		while (x != 0) {
			result = result * 10 + (x % 10);
			x = x / 10;
		}
		return result;
	}

	private static List<Integer> getPrime(int max) {

		int count = 0;
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i <= max; i++) {
			int j = 2;
			for (; j <= i / 2; j++) {
				if (i % j == 0) {
					break;
				}
			}
			if (j > i / 2) {
				primes.add(i);
			}
		}
		return primes;
	}
	
	/**
	 * 最长合法括号——字符串中出现的合法括号中的最长
	 * @param s
	 * @return
	 */
	private static int longestValidParentheses(String s) {
		int result = 0;
		int tmp = 0;
		char[] cs = s.toCharArray();
		Stack<Integer> st = new Stack<Integer>();
		for(int i = 0;i < cs.length; i++) {
			if (st.isEmpty()) {
				st.push(i);
			} else {
				if (cs[i] == ')' && cs[st.peek()] == '(') {
					st.pop();
				} else {
					st.push(i);
				}
			}
		}
		if (st.isEmpty()) {
			result = cs.length;
		} else {
			st.push(cs.length);
			while(!st.isEmpty()) {
				tmp = st.pop() ;
				tmp = st.isEmpty() ? tmp: (tmp - st.peek() - 1);
				result = result < tmp ? tmp : result; 
			}
		}
		return result;
	}
	
	/**
	 * 倒水——a,b两个容器，可否倒满c容器
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private static boolean can(int a,int b,int c) {
		if (c % a == 0 || c % b == 0) {
			return true;
		}
		if (a == b) {
			return false;
		}
		int s = a > b ? b : a;
		int l = a > b ? a : b;
		int l_left = l;
		while(l_left % s != 0) {
			if (c < s) {
				if (c == l_left % s) {
					return true;
				}
			} else if (c > s && c < l_left) {
				if ((l_left - (l_left % s)) % s == 0) {
					return true;
				}
			} else {
				if (c % l_left == 0 || (c % (l_left % s) == 0)) {
					return true;
				}
			}
			l_left = l + l_left % s - s;
		}
		return false;
	}
	
	/**
	 * 字符串完美度——字符串中出现的字母分配1-26不同的数字，求使所有数字之和最大的分配方案
	 * @param s
	 * @return
	 */
	public static int perfect(String s) {
		char[] cs = s.toUpperCase().toCharArray();
		int[] ca = new int[26];
		int max = 26;
		int tmp = 0;
		int result = 0;
		for (char c : cs) {
			ca[c - 'A'] += 1;
		}
		// 选择排序
		for (int i = 0; i < ca.length; i++) {
			for (int j = i + 1; j < ca.length; j++) {
				if (ca[j] > ca[i]) {
					tmp = ca[i];
					ca[i] = ca[j];
					ca[j] = tmp;
				}
			}
			if (ca[i] == 0) {
				break;
			}
			result += max * ca[i];
			max--;
		}
		return result;
	}

	/**
	 * 24点游戏——4组数字可否计算出24
	 * @param num
	 * @param result
	 * @param stack
	 * @return
	 */
	private static boolean get24(List<int[]> num, int[] result, List<String> stack) {
		if (num.size() == 1) {
			int[] oper = num.get(0);
			if (oper[0] == result[0] && oper[1] == result[1]) {
				stack.add(String.valueOf(oper[0]));
				return true;
			}
			return false;
		}
		List<int[]> tmp = new ArrayList<int[]>();
		for (int[] i : num) {
			tmp.add(i);
		}
		for (int[] i : num) {
			tmp.remove(i);
			// tmp - i = result
			if (get24(tmp, calc(result, i, '+'), stack)) {
				stack.add(0, "(");
				stack.add("-" + String.valueOf(i[0]));
				return true;
			// i - tmp = result
			} else if (get24(tmp, calc(i, result, '-'), stack)) {
				stack.add(0, "(" + String.valueOf(i[0]) + "-");
				stack.add(")");
				return true;
			// tmp + i = result;
			} else if (get24(tmp, calc(result, i, '-'), stack)) {
				stack.add(String.valueOf('+'));
				stack.add(String.valueOf(i[0]));
				return true;
			// tmp / i = result
			} else if (get24(tmp, calc(result, i, '*'), stack)) {
				stack.add("/" + String.valueOf(i[0]));
				return true;
			// i / tmp = result
			} else if (get24(tmp, calc(i, result, '/'), stack)) {
				stack.add(0, String.valueOf(i[0]) + "/");
				return true;
			// tmp * i = result
			} else if (get24(tmp, calc(result, i, '/'), stack)) {
				stack.add("*" + String.valueOf(i[0]));
				return true;
			}
			tmp.add(i);
		}
		return false;
	}

	private static int[] calc(int[] a, int[] b, char calculator) {
		int opbS = b[0];
		int opbP = b[1];
		System.out.println(a[0] + "/" + a[1] + "  " +  calculator + "  " + b[0] + "/" + b[1]);
		int[] c = new int[2];
		if (calculator == '-') {
			opbS = 0 - opbS;
			calculator = '+';
		} else if (calculator == '/') {
			if (opbS == 0 || opbP == 0) {
				return c;
			}
			int tmp = opbS;
			opbS = opbP;
			opbP = tmp;
			calculator = '*';
		}
		if (calculator == '+') {
			if (a[1] == opbP) {
				c[1] = a[1];
				c[0] = a[0] + opbS;
			} else {
				c[1] = a[1] * opbP;
				c[0] = a[0] * opbP + a[1] * opbS;
			}	
		} else if (calculator == '*') {
			c[0] = a[0] * opbS;
			c[1] = a[1] * opbP;
		}
		if (c[1] != 1 && c[0] % c[1] == 0) {
			c[0] = c[0] / c[1];
			c[1] = 1;
		}
		return c;
	}

	private static void huiwen(int n, int[] m) {
		
		List<Integer> primes = getPrime(n);
		int[] nv = new int[primes.size()];
		int i = 0;
		for (int p : primes) {
			nv[i++] = getPrimePa(n, p);
		}
		i = 0;
		for (int mi : m) {
			for (int p : primes) {
				nv[i] -= getPrimePa(mi, p);
				i++;
			}
			i = 0;
		}
		i = 0;
		long result = 1;
		for (int p : primes) {
			result = (long) ((result * pow(p, nv[i], 1000000007)) % 1000000007);
			i++;
		}
		System.out.println(result);
	}
	
	/**
	 * 模取幂 n^k mod m——反复平方法（二分法 ab mod c = (a mod c)(b mod c) mod c）
	 * @param n
	 * @param k
	 * @param m
	 * @return
	 */
	private static long pow(long n, int k, int m) {
		long res = 1;
		while (k > 0) {
		
			if ((k & 1) == 1) res = (res * n) % m;
			n = (n * n) % m;
			k >>= 1;
		}
		return res;
	}
	
	/**
	 * 计算m!中质因子个数
	 * @param m
	 * @param p 质因子
	 * @return
	 */
	private static int getPrimePa(int m, int p) {
		int tmp = p;
		int pa = 0;
		while (m >= tmp) {
			pa += m / tmp;
			tmp *= p;
		}
		return pa;
	}
	
	public static int run(int[] a) {
		int count = 0;
		int tmp = 0;
		int swap = 0;
		for (int i = 0; i < a.length; i++) {
			int j = i;
			while (a[j] != (j + 1)) {
				swap = a[j] - 1;
				tmp = a[j];
				a[j] = a[swap];
				a[swap] = tmp;
				count++;
			}
		}
		return count;
	}
	
	public static int run1(int[] a) {
		Set<String> s = new HashSet<String>();
		for (int l = 0; l < a.length; l++) {
			for (int i = 0; i < a.length; i++) {
				for (int j = i; j < a.length - l; j++) {
					int tmp = 0;
					StringBuilder sb = new StringBuilder(String.valueOf(a[j+tmp]));
					while (tmp++ < l) {
						sb.append(a[j+tmp]);
					}
					s.add(sb.toString());
				}
			}
		}
		for (Iterator<String> iterator = s.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			System.out.println(str);
		}
		return s.size();
	}
}
