package douyu;

class Test {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("douyu.B");
	}

}

class A {
	static {
		System.out.println("A");
	}

	void aa() {
		System.out.println("A���");

	}

}

class B extends A {
	static {
		System.out.println("B");
	}

	void aa() {
		System.out.println("B���");
	}

}

class C extends A {
	static {
		System.out.println("C");
	}

	void aa() {
		System.out.println("C���");

	}

}
