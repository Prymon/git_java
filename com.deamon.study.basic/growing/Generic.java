package growing;

/**
 * ����
 */
public class Generic {
	// ����Demo
	public static void main(String[] args) {
		String[] strs = new String[] { "1", "2", "3" };
		strs = Change.change(strs, 0, 2);
		for (String temp : strs) {
			System.out.println(temp);
		}
	}
}

/**
 * ���嵽���ϵķ���
 */
class Item<T> {

	private T t;

	public Item(T t) {
		this.t = t;
	}
}

/**
 * ���嵽�����ķ���
 */
class Item2 {

	public <T> T get(T t) {
		return t;
	}
}

/**
 * Demo1,���������ض�λ��Ԫ��
 */
class Change {

	// ��̬�����ķ��ͱ��뵥������
	public static <T> T[] change(T[] arr, int index1, int index2) {
		T t = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = t;
		return arr;
	}
}
