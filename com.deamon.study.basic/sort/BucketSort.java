package sort;

/**
 * 数据结构-排序-桶式排序 假设现在有一组小于M的正整数 a1、 a2 ，…… ，an 。 对它们排序可以采用以下的思路：
 * 使用一个大小为M的数组buckets，这个数组的每一个单元称为一个个的bucket，桶，初始化全部为0。
 * 扫描数组a，当扫描到ai的时候，buckets[ai] 加1。这样当a扫描完之后，扫描buckets，打印非零单元的下标，它的值是几就打印几次。
 * 打印出来的值实际上就是排好序之后的数组a了。我们可以依次把它们赋值给a，使得a有序。
 */
public class BucketSort {

	public static void main(String[] args) {
		int[] nums = new int[] { 5, 3, 2, 4, 1, 4, 0, -1 };
		int max = 5;
		try {
			nums = sort_1(nums, max);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		for (int t : nums) {
			System.out.println(t);
		}
	}

	/**
	 * @param nums 限制：必须大于等于0
	 * @param max 最大的值
	 * @return 排序后的数组
	 * @throws Exception
	 */
	private static int[] sort_1(int[] nums, int max) throws Exception {
		if (!valid(nums, max)) {
			throw new Exception("input not valid,please check!");
		}
		int[] buckets = new int[max + 1]; // 下标+1以对应最大值
		for (int t : nums) {
			buckets[t]++;
		}
		int count = 0; // 桶数组下标，即对应的数
		int countnums = 0; // 用于存储的下标
		for (int t : buckets) {
			for (int i = 0; i < t; i++) {
				nums[countnums] = count;
				countnums++;
			}
			count++;
		}
		return nums;
	}

	/**
	 * 检测输入是否合法
	 * 
	 * @param nums 不允许含有负数
	 * @param max 必须是正确的最大值
	 * @return
	 */
	private static boolean valid(int[] nums, int max) {
		boolean isvalid = true;
		for (int temp : nums) {
			if (temp <= 0 || temp > max) {
				isvalid = false;
			}
		}
		return isvalid;
	}
}
