package search;

public class BinSearch {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 3, 4 };
		System.out.println(insert(nums, 5));
	}

	/**
	 * �۰���� ����-1��δ�ҵ�
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
	 * �۰������� [0][1][2][3] ���ز嵽�ĸ���ǰ����[0][1][2][4] ����3�� ����3��
	 */
	private static int insert(int[] nums, int number) {
		int length = nums.length;
		int first = 0;
		int last = length - 1;
		int mid = (first + last) / 2;
		// һ���Ǵ��ڵ��ڣ��ڵ��ڵ�ʱ��Ҳ��Ҫ��һ��ȷ��������ǰ���Ǻ�
		while (last - first >= 0) {
			mid = (first + last) / 2;
			if (nums[mid] == number) // ����ҵ���ͬ������뵽��֮�󣬱�������ƶ�
				return mid + 1;
			if (nums[mid] > number)
				last = mid - 1;
			if (nums[mid] < number)
				first = mid + 1;
		}
		return first;
	}

}
