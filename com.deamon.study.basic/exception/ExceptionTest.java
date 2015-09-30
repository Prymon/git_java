package exception;

import org.junit.Test;;

/**
 * Throwable 类是 Java 语言中所有错误或异常的超类。 只有当对象是此类（或其子类之一）的实例时，才能通过 Java 虚拟机或者 Java
 * throw 语句抛出。 类似地，只有此类或其子类之一才可以是 catch 子句中的参数类型。 Throwable有两个子类，Error,Exception
 * Error：严重的错误。 如内存溢出等。 Exception:异常。 如除0，数组越界
 * 
 * *throws 和 throw关键字区别： -throws用在方法声明处。表示可能抛出多个异常，后面跟异常类名。 -throw
 * 用在方法内部。后面跟要抛出的异常对象。如 throw new Exception();
 * 
 * Exception 和 RuntimeException RuntimeException为运行时异常，不用try catch
 * 语句捕获也不会报错。如ArithmeticException， IndexOutOfBoundsException
 */
public class ExceptionTest {

	@Test // 测试除零 和 数组下标越界
	public void test1() {
		try {
			A a = new A();
			a.div(2, 2);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage() + "1" + e.toString());
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage() + e.toString());
		} finally {

		}
	}

	@Test // 测试RuntimeException ，由于test1接受的都是RuntimeException。所以
	public void test2() {
		A a = new A();
		a.div(2, 2);
	}

	@Test // 测试自定义异常
	public void test3() {
		try {
			new A().myexcp(2, -1);
		} catch (DivisorIsNegativeException e) {
			System.out.println(e.toString());
		}
	}

}

class A {
	public int div(int a, int b) throws ArithmeticException, ArrayIndexOutOfBoundsException {
		int[] nums = new int[a];
		nums[b] = 0;
		return a / b;
	}

	public int myexcp(int a, int b) throws DivisorIsNegativeException {
		if (b < 0) {
			throw new DivisorIsNegativeException("除数不能为负数", b);
		} else {
			return a / b;
		}
	}
}

/**
 * 自定义异常
 */
class DivisorIsNegativeException extends Exception {
	public DivisorIsNegativeException(String msg, int divisor) {
		super(msg);
		System.out.println("error divisor:" + divisor);
	}
}
