package search;

public class BinSearch {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 3, 4 };
		System.out.println(insert(nums, 5));
	}

	/**
	 * 折半查找 返回-1则未找到
	 */
	private static int search(int[] nums, int number) {
		int length = nums.length;
		int min = 0, max = length - 1, mid = 0;
		while (max != min) {
			mid = (min + max) / 2;
			if (nums[mid] == number)
				return mid;
			if (nums[mid] < number)
				min = mid + 1;
			if (nums[mid] > number)
				max = mid - 1;
			System.out.println(min + "-" + mid + "-" + max);
		}
		return nums[min] == number ? min : -1;
	}

	/**
	 * 折半插入查找 [0][1][2][3] 返回插到哪个数前。如[0][1][2][4] 插入3， 返回3；
	 */
	private static int insert(int[] nums, int number) {
		int length = nums.length;
		int first = 0;
		int last = length - 1;
		int mid = (first + last) / 2;
		// 一定是大于等于，在等于的时候也需要进一步确定插入在前还是后。
		while (last - first >= 0) {
			mid = (first + last) / 2;
			if (nums[mid] == number) // 如果找到相同，则插入到它之后，避免多余移动
				return mid + 1;
			if (nums[mid] > number)
				last = mid - 1;
			if (nums[mid] < number)
				first = mid + 1;
		}
		return first;
	}

}
