package thread;

public class SingletonPattern {

}

// ����ʽ----�̰߳�ȫ
class Single1 {
	private static final Single1 instance = new Single1();

	private Single1() {
	}

	public static Single1 getInstance() {
		return instance;
	}
}

// ����ʽ-----���̰߳�ȫ
class Single2_1 {
	private static Single2_1 instance = null;

	private Single2_1() {
	};

	public static Single2_1 getInstance() {
		if (instance == null) {
			instance = new Single2_1(); // ���̰߳�ȫ��
		}
		return instance;
	}
}

// ����ʽ-----�̰߳�ȫ--��Ч
class Single2_2 {
	private static Single2_2 instance = null;

	private Single2_2() {
	};

	public static synchronized Single2_2 getInstance() {
		if (instance == null) {
			instance = new Single2_2(); // �̰߳�ȫ���ظ�����ͬ����������Ч��
		}
		return instance;
	}
}

// ����ʽ-----�̰߳�ȫ--��Ч
class Single2_3 {
	private static Single2_3 instance = null;

	private Single2_3() {
	};

	public static Single2_3 getInstance() {
		if (instance == null) {
			synchronized (Single2_3.class) { // ��֤�����ƻ�����ģʽ
				if (instance == null) {
					instance = new Single2_3();
				}
			}
		}
		return instance;
	}
}
