package thread;

/**
 * 进程1 拿A锁等B 进程2 拿B锁等A 于是死锁了
 */
public class DeadLockThreadDeamo {
	public static void main(String[] args) {
		new Thread(new TestLock1()).start();
		new Thread(new TestLock2()).start();
	}
}

// 存放两个锁
class Lock {
	public static Object A = new Object();
	public static Object B = new Object();
}

// 进程1 拿A锁等B
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

// 进程2 拿B锁等A
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