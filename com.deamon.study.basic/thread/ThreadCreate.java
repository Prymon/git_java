package thread;

public class ThreadCreate {
	public static void main(String[] args) {
		Thread myt1 = new MyThread1();
		myt1.start();
		System.out.println(myt1.getName());
	}

}

/**
 * 方法1：继承Thread 类
 */
class MyThread1 extends Thread {
	// 重写父类run方法
	public void run() {
		for (int i = 0; i < 60; i++) {
			System.out.println("now" + i);
		}
	}
}

/**
 * 方法2：实现Runnable 接口
 */
