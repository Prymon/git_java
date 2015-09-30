package sort;

import java.util.Random;

/**
 * 插入式排序是一个排序种类 思想：在一个已经排好序的序列中，将未被排进的元素按照原先的规定插入到指定位置。
 */
public class InsertSort {

	public static void main(String[] args) {
		int[] arrs = getRandomArrs();
		int[] arrs2 = arrs.clone();
		String str1 = "", str2 = "";
		arrs = getRandomArrs();
		long time1 = System.currentTimeMillis();
		for (int t : directInsertSort(arrs)) {
			str1 += t + " ";
		}
		time1 = System.currentTimeMillis() - time1;
		long time2 = System.currentTimeMillis();
		for (int t : binInsertSort(arrs2)) {
			str2 += t + " ";
		}
		time2 = System.currentTimeMillis() - time2;
		System.out.println(time1 + "\n" + time2);
	}

	/**
	 * 直接插入排序(升序) 思想：最基本的插入排序，将第i个插入到前i-1个中的适当位置。 时间复杂度：T(n) = O(n^2)。
	 * 空间复杂度：S(n) = O(1)。 稳定性：稳定排序。循环条件while(r[0].key < r[j].key)保证的。
	 */
	public static int[] directInsertSort(int[] nums) {
		int length = nums.length;
		int temp = 0;
		for (int i = 1; i < length; i++) {
			temp = nums[i];
			int j = i - 1;
			while (j >= 0 && temp < nums[j]) {
				nums[j + 1] = nums[j];
				j--;
			}
			nums[j + 1] = temp;
		}
		return nums;
	}

	/**
	 * 折半插入排序 思想：因为是已经确定了前部分是有序序列，所以在查找插入位置的时候可以用折半查找的方法进行查找，提高效率。 （1）计算 0 ~ i-1
	 * 的中间点，用 i 索引处的元素与中间值进行比较，如果 i
	 * 索引处的元素大，说明要插入的这个元素应该在中间值和刚加入i索引之间，反之，就是在刚开始的位置 到中间值的位置，这样很简单的完成了折半；
	 * （2）在相应的半个范围里面找插入的位置时，不断的用（1）步骤缩小范围，不停的折半，范围依次缩小为 1/2 1/4 1/8
	 * .......快速的确定出第 i 个元素要插在什么地方； （3）确定位置之后，将整个序列后移，并将元素插入到相应位置。
	 * 时间复杂度：T(n)=O(n^2)。 空间复杂度：S(n) = O(1)。 稳定性：稳定排序。
	 */
	public static int[] binInsertSort(int[] nums) {
		int length = nums.length;
		for (int i = 1; i < length; i++) {
			int temp = nums[i];
			// 折半寻找插入位置
			int first = 0, last = i - 1;
			int mid = (first + last) / 2;
			// 已经符合顺序，则不用插入
			if (nums[i] >= nums[last]) {
				continue;
			}
			// 否则在前i-1中寻找合适位置
			while (last - first >= 0) {
				if (temp == nums[mid]) {
					first = mid + 1;
					break;
				}
				if (temp < nums[mid]) {
					last = mid - 1;
				}
				if (temp > nums[mid]) {
					first = mid + 1;
				}
				mid = (first + last) / 2;
			}
			// 插入到fisrt下标位置
			for (int j = i; j > first; j--) {
				nums[j] = nums[j - 1];
			}
			nums[first] = temp;
		}
		return nums;
	}

	// 得到随机大小和值的int数组
	private static final int SIZE = 100000;
	private static final int MAX = 20000;

	private static int[] getRandomArrs() {
		Random r = new Random();
		int size = SIZE;
		int[] nums = new int[size];
		for (int i = 0; i < size; i++) {
			nums[i] = r.nextInt(MAX);
		}
		return nums;
	}
}
