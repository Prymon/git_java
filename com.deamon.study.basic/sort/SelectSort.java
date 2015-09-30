package sort;

import java.util.Random;

/**
 * ˼�룺ÿһ����n �C i + 1 ( i = 1,2, �� , n - 1)����¼��ѡȡ�ؼ�����С�ļ�¼��Ϊ���������еĵ�i����¼��
 */
public class SelectSort {

	public static void main(String[] args) {
		// ����1��
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
	 * ��ѡ������ ˼�룺ÿһ�˴�n-i+1����¼��ѡ����Сֵ���һ��������Ȼ�󽫵�һ����������ȥ�� ʱ�临�Ӷȣ�T(n) = O(n^2)��
	 * �ռ临�Ӷȣ�S(n) = O(1)�� �ȶ��ԣ����ȶ�����
	 */
	public static int[] simpleSelectSort(int[] nums) {
		int length = nums.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = i; j < length; j++) {
				if (nums[i] > nums[j]) {
					// �������ʱ��������λ��
					nums[i] += nums[j];
					nums[j] = nums[i] - nums[j];
					nums[i] = nums[i] - nums[j];
				}
			}
		}
		return nums;
	}

	// �õ������С��ֵ��int����
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
