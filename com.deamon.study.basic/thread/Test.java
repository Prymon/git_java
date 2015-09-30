package thread;

import java.util.concurrent.locks.ReentrantLock;

public class Test {

}

class SingleEhan {
	private static final SingleEhan ehan = new SingleEhan();

	private SingleEhan() {
	}

	public SingleEhan getInstance() {
		return this.ehan;
	}
}

class SingleLhan {
	private static SingleLhan lhan = null;

	private SingleLhan() {
	}

	public SingleLhan getInstance() {
		if (lhan == null) {
			ReentrantLock lock = new ReentrantLock();
			lock.lock(); // Í¬²½Ëø¡£±ÜÃâÆÆ»µµ¥Àý
			if (lhan == null) {
				lhan = new SingleLhan();
			}
			lock.unlock();
		}
		return this.lhan;
	}

}