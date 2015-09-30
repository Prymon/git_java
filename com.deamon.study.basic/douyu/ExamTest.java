package douyu;

import org.omg.CORBA.Current;

public class ExamTest {
	private static int count = 0;

	public static void main(String[] args) {
		Addth add = new Addth();
		Decth dec = new Decth();
		for (int i = 0; i < 2; i++) {
			new Thread(add).start();
			new Thread(dec).start();
		}
	}

	public synchronized static void add() {
		count++;
		System.out.println(Thread.currentThread().getName() + "Add" + count);
	}

	public synchronized static void dec() {
		count--;
		System.out.println(Thread.currentThread().getName() + "Dec" + count);
	}

}

class Addth implements Runnable {
	public void run() {
		while (true) {
			ExamTest.add();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Decth implements Runnable {
	public void run() {
		while (true) {
			ExamTest.dec();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
