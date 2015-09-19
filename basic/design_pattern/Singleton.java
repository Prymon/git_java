package design_pattern;

/**
 * 单例模式
 * 
 * @author Deamon
 *
 */
public class Singleton {
	public static void main(String[] args) {

	}
}

/**
 * 饿汉式 最简单的实现 但是无法控制实例的加载,这种实现方式，在面对JVM自由的编译器优化时会产生问题
 * 
 * @author Deamon
 */
class Singleton_1 {
	private static Singleton_1 instance = new Singleton_1();

	private Singleton_1() {
	}

	public static Singleton_1 getInstance() {
		return instance;
	}

}

/**
 * 懒汉式 JVM只是一个标准，并不是实现。JVM中并没有规定有关编译器优化的内容，也就是说，JVM实现可以自由的进行编译器优化。
 * 
 */
class Singleton_2 {
	private static Singleton_2 instance = null;

	private Singleton_2() {
	}

	public static Singleton_2 getInstance() {
		if (instance == null) {
			synchronized (Singleton_2.class) {
				if (instance == null)
					instance = new Singleton_2();
			}
		}
		return instance;
	}
}

/**
 * 懒汉式单例由于编译器优化产生的问题：
 * 下面来想一下，创建一个变量需要哪些步骤呢？一个是申请一块内存，调用构造方法进行初始化操作，另一个是分配一个指针指向这块内存。
 * 这两个操作谁在前谁在后呢？JVM规范并没有规定。那么就存在这么一种情况，JVM是先开辟出一块内存，然后把指针指向这块内存，最后调用构造方法进行初始化。
 * 
 *  下面我们来考虑这么一种情况：线程A开始创建SingletonClass的实例，此时线程B调用了getInstance()方法，首先判断instance是否为null。
 *  按照我们上面所说的内存模型，A已经把instance指向了那块内存，只是还没有调用构造方法，因此B检测到instance不为null，于是直接把instance返回了——问题出现了，
 *  尽管instance不为null，但它并没有构造完成，就像一套房子已经给了你钥匙，但你并不能住进去，因为里面还没有收拾。
 *  此时，如果B在A将instance构造完成之前就是用了这个实例，程序就会出现错误了！
 *  在jdk1.5之后的版本，可以使用volatile关键字
 */
class Singleton_3 {
	private static volatile Singleton_3 instance = null;

	private Singleton_3() {
	}

	public static Singleton_3 getInstance() {
		if (instance == null) {
			synchronized (Singleton_3.class) {
				if (instance == null)
					instance = new Singleton_3();
			}
		}
		return instance;
	}
}










