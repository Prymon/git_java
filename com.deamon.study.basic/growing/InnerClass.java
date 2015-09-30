package growing;

import java.util.Queue;

public class InnerClass {
	public static void main(String[] args) {
		InnerClass t = new InnerClass();
		InnerClass.son s = t.new son();
		InnerClass.sson ss = new InnerClass.sson();
		System.out.println(t);
		System.out.println(s);
		System.out.println(ss);
	}

	public String toString() {
		return "parent";
	}

	/**
	 * 普通内部类，必须有外部类的实例才能使用
	 */
	class son {
		public String toString() {
			return "son";
		}
	}

	/**
	 * 静态内部类，
	 */
	static class sson {
		public String toString() {
			return "static son";
		}
	}
}