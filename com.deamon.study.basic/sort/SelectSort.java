package sort;

import java.util.Random;

/**
 * 思想：每一趟在n – i + 1 ( i = 1,2, … , n - 1)个记录中选取关键字最小的记录作为有序序列中的第i个记录。
 */
public class SelectSort {

	public static void main(String[] args) {
		// 测试1：
		int i = 0;
		for (int t : simpleSelectSort(getRandomArrs())) {
			System.out.print(t + " ");
			if (i++ == 10) {
				System.out.println();
				i = 0;
			}
		}
	}

	/**
	 * 简单选择排序 思想：每一趟从n-i+1个记录中选出最小值与第一个交换，然后将第一个从序列中去掉 时间复杂度：T(n) = O(n^2)。
	 * 空间复杂度：S(n) = O(1)。 稳定性：不稳定排序。
	 */
	public static int[] simpleSelectSort(int[] nums) {
		int length = nums.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i; j < length; j++) {
				if (nums[i] > nums[j]) {
					// 不添加临时变量交换位置
					nums[i] += nums[j];
					nums[j] = nums[i] - nums[j];
					nums[i] = nums[i] - nums[j];
				}
			}
		}
		return nums;
	}

	// 得到随机大小和值的int数组
	private static int[] getRandomArrs() {
		Random r = new Random();
		int size = r.nextInt(100);
		int[] nums = new int[size];
		for (int i = 0; i < size; i++) {
			nums[i] = r.nextInt(80) + 10;
		}
		return nums;
	}
}
