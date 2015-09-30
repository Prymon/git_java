package sort;

public class SheelSort {

	public static void main(String[] args) {
		int[] s = shellsort(new int[] { 49, 38, 65, 97, 26, 13, 27, 49, 55, 4 });
		for (int t : s) {
			System.out.print(t + " ");
		}
	}

	/**
	 * ϣ������(Shell Sort)�ǲ��������һ�֡�Ҳ����С����������ֱ�Ӳ��������㷨��һ�ָ���Ч�ĸĽ��汾��
	 * ϣ�������Ƿ��ȶ������㷨���÷�����DL��Shell��1959������������� �����С�49, 38, 65, 97, 26, 13, 27, 49,
	 * 55, 4�� ��һ�� gap = 10 / 2 = 5
	 * [49,13],[38,27],[65,49],[97,55],[26,4]�ֱ���в�������󣬺ϲ�Ϊ�� ��13, 27, 49, 55, 4,
	 * 49, 38, 65, 97, 26�� �ڶ��� gap = 5/2 = 2
	 * [49,65,26,27,55],[38,97,13,49,4]�ٴν��в������򣺺ϲ�Ϊ�� ��13,27,49,55,4,
	 * 49,38,65,97,26�� ������gap = 2/2 = 1 [13,27,49,55,4,
	 * 49,38,65,97,26]����һ�������Ĳ������� ��4 �� 26 �� 13 �� 27 �� 38 �� 49 �� 49 �� 55 �� 97 ��
	 * 65��
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
