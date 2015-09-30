package thread;

/**
 * ����1 ��A����B ����2 ��B����A ����������
 */
public class DeadLockThreadDeamo {
	public static void main(String[] args) {
		new Thread(new TestLock1()).start();
		new Thread(new TestLock2()).start();
	}
}

// ���������
class Lock {
	public static Object A = new Object();
	public static Object B = new Object();
}

// ����1 ��A����B
class TestLock1 implements Runnable {
	public void run() {
		while (true) {
			synchronized (Lock.A) {
				synchronized (Lock.B) {
					System.out.println("Thread_1");
				}
			}
		}
	}
}

// ����2 ��B����A
class TestLock2 implements Runnable {
	public void run() {
		while (true) {
			synchronized (Lock.B) {
				synchronized (Lock.A) {
					System.out.println("Thread_2");
				}
			}
		}
	}
}