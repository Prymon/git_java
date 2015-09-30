package growing;

public class Feibonacci {
	private static int getsumn(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += getn(i);
		}
		return sum;
	}

	private static int getn(int n) {
		int a = 1, b = 1;
		if (n <= 2)
			return 1;
		for (int i = 1; i < n - 1; i++) {
			b = a + b;
			a = b - a;
		}
		return b;
	}

	private static int getn2(int n) {
		if (n <= 2)
			return 1;
		else
			return getn2(n - 1) + getn2(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(getsumn(44));
	}
}
