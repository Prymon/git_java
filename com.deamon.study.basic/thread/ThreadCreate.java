package thread;

public class ThreadCreate {
	public static void main(String[] args) {
		Thread myt1 = new MyThread1();
		myt1.start();
		System.out.println(myt1.getName());
	}

}

/**
 * ����1���̳�Thread ��
 */
class MyThread1 extends Thread {
	// ��д����run����
	public void run() {
		for (int i = 0; i < 60; i++) {
			System.out.println("now" + i);
		}
	}
}

/**
 * ����2��ʵ��Runnable �ӿ�
 */
