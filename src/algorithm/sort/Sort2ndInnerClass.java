package algorithm.sort;

import java.util.Arrays;

public class Sort2ndInnerClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sort2ndInnerClass s = new Sort2ndInnerClass();
		int[] a = new int[]{5,2,4,7,1,3,2,6};
		
//		s.getInsetSort().insertionSort(a);
//		MergeSort ms = s.new MergeSort();
//		ms.mergeSort2ndGetInverse(a, 0, a.length - 1);
//		
		Sort2ndInnerClass.getQsortClass(a).sort();
//		s.getHeapSort().min_heap_sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

	}
	

	/**
	 * 字符串完美度——字符串中出现的字母分配1-26不同的数字，求使所有数字之和最大的分配方案
	 * 将字母出现的个数按从大到小排序——选择排序
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
	
	public InsertSort getInsetSort() {
		return new InsertSort();
	}
	public HeapSort getHeapSort() {
		return new HeapSort();
	}

	/**
	 * 匿名内部类——快排（nlgn）
	 * @param a 当所在的方法的形参需要被内部类里面使用时，该形参必须为final
	 * @return Sort接口
	 */
	public static Sort getQsortClass(final int[] sortArray) {
		
		return new Sort() {

			private int[] a = sortArray;

			public void sort() {
				int low = 0;
				int high = sortArray.length - 1;
				qsort(low, high);
			}
			
			public void qsort(int low, int high) {
				if (low < high) {
					int pivotLoc = randomizedPartition(low, high);
					qsort(low, pivotLoc -1);
					qsort(pivotLoc + 1, high);
				}
			}
			
			/**
			 * 两边紧逼重排
			 * @param low
			 * @param high
			 * @return
			 */
			private int partition2way(int low, int high) {
				int pivot = a[low];
				while (low < high) {
					while (low < high && a[high] >= pivot) high--;
					a[low] = a[high];
					while (low < high && a[low] <= pivot) low++;
					a[high] = a[low];
				}
				a[low] = pivot;
				return low;
			}
			
			/**
			 * 随机取得pivot,单头重排
			 * @param low
			 * @param high
			 * @return
			 */
			public int randomizedPartition(int low, int high) {
				int pivot = low + (int)(Math.random() * (high - low));
				int tmp = a[high];
				a[high] = a[pivot];
				a[pivot] = tmp;
				
				return partition1way(low, high);
			}
			
			private int partition1way(int low, int high) {
				int tmp = 0;
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
			 * int[] a = new int[]{54, 543, 545, 546, 5454, 1, 12 , 6};
			 * 依据bigger的排序
			 * @param low
			 * @param high
			 * @return
			 */
			private int partition1(int low, int high) {
				int pivot = a[low];
				while (low < high) {
					while (low < high && bigger(a[high], pivot)) high--;
					a[low] = a[high];
					while (low < high && bigger(pivot, a[low])) low++;
					a[high] = a[low];
				}
				a[low] = pivot;
				return low;
			}
			
			/**
			 * a>=b return true
			 * @param a
			 * @param b
			 * @return
			 */
			private boolean bigger(int a, int b) {
				String as = String.valueOf(a);
				String bs = String.valueOf(b);
				if (as.length() == bs.length()) {
					return a >= b;
				} else {
					if (as.length() < bs.length()) {
						return !compare(String.valueOf(a), String.valueOf(b));
					} else {
						return compare(String.valueOf(b), String.valueOf(a));
					}
				}
			}
			
			/**
			 * ls >= ss return true
			 * @param ss
			 * @param ls
			 * @return
			 */
			private boolean compare(String ss, String ls) {

				while (true) {
					if (ls.startsWith(ss)) {
						if (ls.equals(ss)) {
							return true;
						}
						ls = ls.substring(ss.length());
						if (ls.length() < ss.length() && ss.startsWith(ls)) {
							return true;
						}
					} else {
						int index = 0;
						while (index < ss.length()) {
							if (ls.charAt(index) == ss.charAt(index)) {
								index++;
							} else if (ls.charAt(index) > ss.charAt(index)) {
								return true;
							} else {
								return false;
							}
						}
					}
				}
			}
		};
	}

	/**
	 * 成员内部类——插入排序（n^2）
	 * 插入排序：循环不变式
	 */	
	public class InsertSort {
		public void insertionSort(int[] a) {
			for (int i = 1; i < a.length; i++) {
				int j = i - 1;
				int key = a[i];
				while (j >=0 && key < a[j]) {
					a[j + 1] = a[j];
					j--;
				}
				a[j + 1] = key;
			}
		}
	}
	
	/**
	 * 成员内部类——归并排序（nlgn）
	 * @author zhuxh
	 *
	 */
	public class MergeSort {
		public int mergeSort2ndGetInverse(int[] a,int start, int end) {
			if (start < end) {
				int mid = (start + end) / 2;
				int c1 = mergeSort2ndGetInverse(a, start, mid);
				int c2 = mergeSort2ndGetInverse(a, mid + 1, end);
				int c3 = merge(a, start, mid, end);
				return c1 + c2 + c3;
			}
			return 0;
		}
		
		private int merge(int[] a,int start, int mid, int end) {
			int[] tmp = new int[end - start + 1];
			int j = mid + 1;
			int i = start;
			int k = 0;
			int inverCount = 0;
			while (i <= mid && j <= end) {
				if (a[i] > a[j]) {
					tmp[k] = a[j];
					j++;
				} else {
					tmp[k] = a[i];
					inverCount += j - mid - 1;
					i++;
				}
				k++;
			}
			while (i <= mid) {
				tmp[k] = a[i];
				k++;
				i++;
				inverCount += end - mid;
			}
			while (j <= end) {
				tmp[k] = a[j];
				k++;
				j++;
			}
			for (int l = start; l <= end; l++) {
				a[l] = tmp[l - start];
			}
			return inverCount;
		}
	}
	
	/**
	 * 堆排序
	 * @author zhuxh
	 *
	 */
	public class HeapSort {
		
		public void max_heapify(int[] a, int i, int n) {
			int left = 2 * i + 1;
			int right = 2 * (i + 1);
			int largest = i;
			if (left < n && a[largest] < a[left]) largest = left;
			if (right < n && a[largest] < a[right]) largest = right;
			if (largest != i) {
				int tmp = a[largest];
				a[largest] = a[i];
				a[i] = tmp;
				max_heapify(a, largest, n);
			}
		}
		
		public void min_heapify(int[] a, int i, int n) {
			int left = 2 * i + 1;
			int right = 2 * (i + 1);
			int smallest = i;
			if (left < n && a[smallest] > a[left]) smallest = left;
			if (right < n && a[smallest] > a[right]) smallest = right;
			if (smallest != i) {
				int tmp = a[smallest];
				a[smallest] = a[i];
				a[i] = tmp;
				min_heapify(a, smallest, n);
			}
		}
		
		public void build_max_heap(int[] a) {
			for (int i = a.length / 2; i >= 0; i--) {
				max_heapify(a, i, a.length);
			}
		}
		
		public void build_min_heap(int[] a) {
			for (int i = a.length / 2; i >= 0; i--) {
				min_heapify(a, i, a.length);
			}
		}
		
		/**
		 * 大顶堆-原址升序
		 * 
		 * @param a
		 */
		public void max_heap_sort(int[] a) {
			build_max_heap(a);
			for (int i = a.length - 1; i > 0; i--) {
				int tmp = a[0];
				a[0] = a[i];
				a[i] = tmp;
				max_heapify(a, 0, i);
			}
		}
		
		/**
		 * 小顶堆-原址逆序
		 * 
		 * @param a
		 */
		public void min_heap_sort(int[] a) {
			build_min_heap(a);
			for (int i = a.length - 1; i > 0; i--) {
				int tmp = a[0];
				a[0] = a[i];
				a[i] = tmp;
				min_heapify(a, 0, i);
			}
		}
	}
	
	/**
	 * 优先队列
	 * @author zhuxh
	 *
	 */
	public class PriorityQueue {
		private HeapSort hs = new HeapSort();
		private int[] s;
		public int maximum() {
			return s[0];
		}
		
		public int extract_max() {
			int r = s[0];
			s[0] = s[s.length - 1];
			int[] ns = Arrays.copyOf(s, s.length - 1);
			s = ns;
			hs.max_heapify(ns, 0, s.length);
			return r;
		}
		
		public void increase_key(int index, int key) {
			if (s== null || index > s.length || key < s[index]) {
				System.out.println("invalid key");
			} else {
				s[index] = key;
				while (index >= 1 && s[index / 2] < s[index]) {
					int tmp = s[index / 2];
					s[index / 2] = s[index];
					s[index] = tmp;
					index /= 2;
				}
			}
		}
		
		public void insert(int key) {
			if (s == null) {
				s = new int[]{key};
			} else {
				s = Arrays.copyOf(s, s.length + 1);
				s[s.length] = Integer.MIN_VALUE;
				increase_key(s.length - 1, key);
			}
			
		}
	}
}
