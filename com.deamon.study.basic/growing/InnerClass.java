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
	 * ��ͨ�ڲ��࣬�������ⲿ���ʵ������ʹ��
	 */
	class son {
		public String toString() {
			return "son";
		}
	}

	/**
	 * ��̬�ڲ��࣬
	 */
	static class sson {
		public String toString() {
			return "static son";
		}
	}
}