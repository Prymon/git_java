package sort;

import java.util.Random;

/**
 * ����ʽ������һ���������� ˼�룺��һ���Ѿ��ź���������У���δ���Ž���Ԫ�ذ���ԭ�ȵĹ涨���뵽ָ��λ�á�
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
	 * ֱ�Ӳ�������(����) ˼�룺������Ĳ������򣬽���i�����뵽ǰi-1���е��ʵ�λ�á� ʱ�临�Ӷȣ�T(n) = O(n^2)��
	 * �ռ临�Ӷȣ�S(n) = O(1)�� �ȶ��ԣ��ȶ�����ѭ������while(r[0].key < r[j].key)��֤�ġ�
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
	 * �۰�������� ˼�룺��Ϊ���Ѿ�ȷ����ǰ�������������У������ڲ��Ҳ���λ�õ�ʱ��������۰���ҵķ������в��ң����Ч�ʡ� ��1������ 0 ~ i-1
	 * ���м�㣬�� i ��������Ԫ�����м�ֵ���бȽϣ���� i
	 * ��������Ԫ�ش�˵��Ҫ��������Ԫ��Ӧ�����м�ֵ�͸ռ���i����֮�䣬��֮�������ڸտ�ʼ��λ�� ���м�ֵ��λ�ã������ܼ򵥵�������۰룻
	 * ��2������Ӧ�İ����Χ�����Ҳ����λ��ʱ�����ϵ��ã�1��������С��Χ����ͣ���۰룬��Χ������СΪ 1/2 1/4 1/8
	 * .......���ٵ�ȷ������ i ��Ԫ��Ҫ����ʲô�ط��� ��3��ȷ��λ��֮�󣬽��������к��ƣ�����Ԫ�ز��뵽��Ӧλ�á�
	 * ʱ�临�Ӷȣ�T(n)=O(n^2)�� �ռ临�Ӷȣ�S(n) = O(1)�� �ȶ��ԣ��ȶ�����
	 */
	public static int[] binInsertSort(int[] nums) {
		int length = nums.length;
		for (int i = 1; i < length; i++) {
			int temp = nums[i];
			// �۰�Ѱ�Ҳ���λ��
			int first = 0, last = i - 1;
			int mid = (first + last) / 2;
			// �Ѿ�����˳�����ò���
			if (nums[i] >= nums[last]) {
				continue;
			}
			// ������ǰi-1��Ѱ�Һ���λ��
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
			// ���뵽fisrt�±�λ��
			for (int j = i; j > first; j--) {
				nums[j] = nums[j - 1];
			}
			nums[first] = temp;
		}
		return nums;
	}

	// �õ������С��ֵ��int����
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
