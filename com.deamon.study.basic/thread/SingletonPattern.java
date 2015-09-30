package thread;

public class SingletonPattern {

}

// 饿汉式----线程安全
class Single1 {
	private static final Single1 instance = new Single1();

	private Single1() {
	}

	public static Single1 getInstance() {
		return instance;
	}
}

// 懒汉式-----非线程安全
class Single2_1 {
	private static Single2_1 instance = null;

	private Single2_1() {
	};

	public static Single2_1 getInstance() {
		if (instance == null) {
			instance = new Single2_1(); // 非线程安全！
		}
		return instance;
	}
}

// 懒汉式-----线程安全--低效
class Single2_2 {
	private static Single2_2 instance = null;

	private Single2_2() {
	};

	public static synchronized Single2_2 getInstance() {
		if (instance == null) {
			instance = new Single2_2(); // 线程安全，重复访问同步锁，降低效率
		}
		return instance;
	}
}

// 懒汉式-----线程安全--高效
class Single2_3 {
	private static Single2_3 instance = null;

	private Single2_3() {
	};

	public static Single2_3 getInstance() {
		if (instance == null) {
			synchronized (Single2_3.class) { // 保证不会破坏单例模式
				if (instance == null) {
					instance = new Single2_3();
				}
			}
		}
		return instance;
	}
}
