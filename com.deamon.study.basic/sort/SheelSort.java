package sort;

public class SheelSort {

	public static void main(String[] args) {
		int[] s = shellsort(new int[] { 49, 38, 65, 97, 26, 13, 27, 49, 55, 4 });
		for (int t : s) {
			System.out.print(t + " ");
		}
	}

	/**
	 * 希尔排序(Shell Sort)是插入排序的一种。也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。
	 * 希尔排序是非稳定排序算法。该方法因DL．Shell于1959年提出而得名。 如序列【49, 38, 65, 97, 26, 13, 27, 49,
	 * 55, 4】 第一次 gap = 10 / 2 = 5
	 * [49,13],[38,27],[65,49],[97,55],[26,4]分别进行插入排序后，合并为： 【13, 27, 49, 55, 4,
	 * 49, 38, 65, 97, 26】 第二次 gap = 5/2 = 2
	 * [49,65,26,27,55],[38,97,13,49,4]再次进行插入排序：合并为： 【13,27,49,55,4,
	 * 49,38,65,97,26】 第三次gap = 2/2 = 1 [13,27,49,55,4,
	 * 49,38,65,97,26]进行一次完整的插入排序 【4 ， 26 ， 13 ， 27 ， 38 ， 49 ， 49 ， 55 ， 97 ，
	 * 65】
	 */
	static int[] shellsort(int nums[]) {
		int length = nums.length;
		for (int gap = length / 2; gap > 0; gap /= 2) {
			for (int i = 0; i < gap; i++) {
				for (int j = i + gap; j < length; j += gap) {
					int temp = nums[j];
					int k = j - gap;
					while (k >= 0 && temp < nums[k]) {
						nums[k + gap] = nums[k];
						k = k - gap;
					}
					nums[k + gap] = temp;
				}
			}

		}
		return nums;
	}

}
