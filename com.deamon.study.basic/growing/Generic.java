package growing;

/**
 * 泛型
 */
public class Generic {
	// 测试Demo
	public static void main(String[] args) {
		String[] strs = new String[] { "1", "2", "3" };
		strs = Change.change(strs, 0, 2);
		for (String temp : strs) {
			System.out.println(temp);
		}
	}
}

/**
 * 定义到类上的泛型
 */
class Item<T> {

	private T t;

	public Item(T t) {
		this.t = t;
	}
}

/**
 * 定义到方法的泛型
 */
class Item2 {

	public <T> T get(T t) {
		return t;
	}
}

/**
 * Demo1,交换数组特定位置元素
 */
class Change {

	// 静态方法的泛型必须单独定义
	public static <T> T[] change(T[] arr, int index1, int index2) {
		T t = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = t;
		return arr;
	}
}
