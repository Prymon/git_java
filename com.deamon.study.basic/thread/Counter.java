package thread;

public class Counter {

	public static void main(String[] args) throws InterruptedException {
		Tick c = new Tick();
		for (int i = 0; i < 100; i++) {
			new Thread(c).start();
		}
		Thread.sleep(500);
		System.out.println(c.count);
	}

}

class Tick implements Runnable {
	public int count = 0;

	public void run() {
		count++;
	}
}