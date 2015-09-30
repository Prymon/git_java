package sort;

/**
 * �������� 1���ȴ�������ȡ��һ������Ϊ��׼���� 2���������̣���������������ȫ�ŵ������ұߣ�С�ڻ����������ȫ�ŵ�������ߡ�
 * 3���ٶ����������ظ��ڶ�����ֱ��������ֻ��һ������
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] nums1 = { 3, 1, 2, 5, 4, 5, 76, 7, 2, 5, 6, 7, 8, 43, 2, 534, 654, 7865, 8, 423, 2, 32, 4, 21, 2, 213, 12,
				5, 423, 65, 46, 2 };
		int[] nums2 = { 3, 1, 2, 5, 4, 5, 76, 7, 2, 5, 6, 7, 8, 43, 2, 534, 654, 7865, 8, 423, 2, 32, 4, 21, 2, 213, 12,
				5, 423, 65, 46, 2 };
		fenzhi(nums1, 0, 8);
		for (int temp : nums1)
			System.out.print(temp);
		System.out.println();
		new QuickSort2().sort(nums2, 0, 8);
		for (int temp : nums2)
			System.out.print(temp);
	}

	public static void fenzhi(int[] nums, int begin, int stop) {
		if (begin < stop) {
			int tag = sort(nums, begin, stop);
			fenzhi(nums, begin, tag - 1);
			fenzhi(nums, tag + 1, stop);
		}
	}

	private static int sort(int[] arrs, int begin, int stop) {
		int x = arrs[begin];
		int i = begin;
		int j = stop;
		// ֱ��ǰ���±���ײ
		while (i < j) {
			// �Ӻ���ǰ
			while (i < j && arrs[j] >= x)
				j--;
			if (i < j) {
				arrs[i] = arrs[j];
				i++;
			}
			// ��ǰ����
			while (i < j && arrs[i] < x)
				i++;
			if (i < j) {
				arrs[j] = arrs[i];
				j--;
			}
		}
		arrs[i] = x;
		return i;
	}

	// ����������Ԫ��λ��
	private static void change(int[] arrs, int a, int b) {
		arrs[a] = arrs[a] + arrs[b];
		arrs[b] = arrs[a] - arrs[b];
		arrs[a] = arrs[a] - arrs[b];
	}
}

/**
 * �Ľ������
 */
class QuickSort2 {
	// ����ֵΪ�������±��ַ
	public void sort(int[] arrs, int begin, int stop) {
		if (begin < stop) { // ��������Խ����ж��ݹ����
			int i = begin, j = stop;
			int hole = arrs[i];
			while (i < j) {
				while (i < j && arrs[j] >= hole)
					j--;
				if (i < j)
					arrs[i++] = arrs[j];
				while (i < j && arrs[i] < hole)
					i++;
				if (i < j)
					arrs[j--] = arrs[i];
			}
			arrs[i] = hole;
			// �ݹ����
			sort(arrs, begin, i - 1);
			sort(arrs, i + 1, stop);
		}
	}
}
