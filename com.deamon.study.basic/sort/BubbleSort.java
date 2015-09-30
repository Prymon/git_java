package sort;

import java.util.Random;

public class BubbleSort {
	public static void main(String[] args) {
		int arrs[] = getRandomArrs();
		sort(arrs);
		for (int temp : arrs)
			System.out.print(temp + " ");
	}

	public static void sort(int[] arrs) {
		for (int j = arrs.length; j > 0; j--) {
			for (int i = 0; i < j - 1; i++) {
				if (arrs[i] > arrs[i + 1]) {
					arrs[i] = arrs[i] + arrs[i + 1];
					arrs[i + 1] = arrs[i] - arrs[i + 1];
					arrs[i] = arrs[i] - arrs[i + 1];
				}
			}
		}
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
