package sort;

/**
 * 快速排序 1．先从数列中取出一个数作为基准数。 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 * 3．再对左右区间重复第二步，直到各区间只有一个数。
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] nums1 = { 3, 1, 2, 5, 4, 5, 76, 7, 2, 5, 6, 7, 8, 43, 2, 534, 654, 7865, 8, 423, 2, 32, 4, 21, 2, 213, 12,
				5, 423, 65, 46, 2 };
		int[] nums2 = { 3, 1, 2, 5, 4, 5, 76, 7, 2, 5, 6, 7, 8, 43, 2, 534, 654, 7865, 8, 423, 2, 32, 4, 21, 2, 213, 12,
				5, 423, 65, 46, 2 };
		fenzhi(nums1, 0, 8);
		for (int temp : nums1)
			System.out.print(temp);
		System.out.println();
		new QuickSort2().sort(nums2, 0, 8);
		for (int temp : nums2)
			System.out.print(temp);
	}

	public static void fenzhi(int[] nums, int begin, int stop) {
		if (begin < stop) {
			int tag = sort(nums, begin, stop);
			fenzhi(nums, begin, tag - 1);
			fenzhi(nums, tag + 1, stop);
		}
	}

	private static int sort(int[] arrs, int begin, int stop) {
		int x = arrs[begin];
		int i = begin;
		int j = stop;
		// 直到前后下标相撞
		while (i < j) {
			// 从后往前
			while (i < j && arrs[j] >= x)
				j--;
			if (i < j) {
				arrs[i] = arrs[j];
				i++;
			}
			// 从前往后
			while (i < j && arrs[i] < x)
				i++;
			if (i < j) {
				arrs[j] = arrs[i];
				j--;
			}
		}
		arrs[i] = x;
		return i;
	}

	// 交换数组两元素位置
	private static void change(int[] arrs, int a, int b) {
		arrs[a] = arrs[a] + arrs[b];
		arrs[b] = arrs[a] - arrs[b];
		arrs[a] = arrs[a] - arrs[b];
	}
}

/**
 * 改进版快排
 */
class QuickSort2 {
	// 返回值为整理后的下标地址
	public void sort(int[] arrs, int begin, int stop) {
		if (begin < stop) { // 避免数组越界和判定递归出口
			int i = begin, j = stop;
			int hole = arrs[i];
			while (i < j) {
				while (i < j && arrs[j] >= hole)
					j--;
				if (i < j)
					arrs[i++] = arrs[j];
				while (i < j && arrs[i] < hole)
					i++;
				if (i < j)
					arrs[j--] = arrs[i];
			}
			arrs[i] = hole;
			// 递归调用
			sort(arrs, begin, i - 1);
			sort(arrs, i + 1, stop);
		}
	}
}
