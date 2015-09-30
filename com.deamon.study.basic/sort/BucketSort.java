package sort;

/**
 * ���ݽṹ-����-Ͱʽ���� ����������һ��С��M�������� a1�� a2 ������ ��an �� ������������Բ������µ�˼·��
 * ʹ��һ����СΪM������buckets����������ÿһ����Ԫ��Ϊһ������bucket��Ͱ����ʼ��ȫ��Ϊ0��
 * ɨ������a����ɨ�赽ai��ʱ��buckets[ai] ��1��������aɨ����֮��ɨ��buckets����ӡ���㵥Ԫ���±꣬����ֵ�Ǽ��ʹ�ӡ���Ρ�
 * ��ӡ������ֵʵ���Ͼ����ź���֮�������a�ˡ����ǿ������ΰ����Ǹ�ֵ��a��ʹ��a����
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
	 * @param nums ���ƣ�������ڵ���0
	 * @param max ����ֵ
	 * @return ����������
	 * @throws Exception
	 */
	private static int[] sort_1(int[] nums, int max) throws Exception {
		if (!valid(nums, max)) {
			throw new Exception("input not valid,please check!");
		}
		int[] buckets = new int[max + 1]; // �±�+1�Զ�Ӧ���ֵ
		for (int t : nums) {
			buckets[t]++;
		}
		int count = 0; // Ͱ�����±꣬����Ӧ����
		int countnums = 0; // ���ڴ洢���±�
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
	 * ��������Ƿ�Ϸ�
	 * 
	 * @param nums �������и���
	 * @param max ��������ȷ�����ֵ
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
