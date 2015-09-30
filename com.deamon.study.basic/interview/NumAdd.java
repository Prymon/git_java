package interview;

/**
 * 用递归的方式计算1-2+3-4+5-6+...+n
 *
 */
public class NumAdd {

	public static void main(String[] args) {
		int res = getResult(100);
		System.out.println(res);
	}

	public static int getResult(int n) {
		if (n == 1) {
			return 1;
		} else if (n % 2 == 0) {
			return n * -1 + getResult(n - 1);
		}
		return n + getResult(n - 1);
	}

}
