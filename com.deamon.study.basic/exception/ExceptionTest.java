package exception;

import org.junit.Test;;

/**
 * Throwable ���� Java ���������д�����쳣�ĳ��ࡣ ֻ�е������Ǵ��ࣨ��������֮һ����ʵ��ʱ������ͨ�� Java ��������� Java
 * throw ����׳��� ���Ƶأ�ֻ�д����������֮һ�ſ����� catch �Ӿ��еĲ������͡� Throwable���������࣬Error,Exception
 * Error�����صĴ��� ���ڴ�����ȡ� Exception:�쳣�� ���0������Խ��
 * 
 * *throws �� throw�ؼ������� -throws���ڷ�������������ʾ�����׳�����쳣��������쳣������ -throw
 * ���ڷ����ڲ��������Ҫ�׳����쳣������ throw new Exception();
 * 
 * Exception �� RuntimeException RuntimeExceptionΪ����ʱ�쳣������try catch
 * ��䲶��Ҳ���ᱨ����ArithmeticException�� IndexOutOfBoundsException
 */
public class ExceptionTest {

	@Test // ���Գ��� �� �����±�Խ��
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

	@Test // ����RuntimeException ������test1���ܵĶ���RuntimeException������
	public void test2() {
		A a = new A();
		a.div(2, 2);
	}

	@Test // �����Զ����쳣
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
			throw new DivisorIsNegativeException("��������Ϊ����", b);
		} else {
			return a / b;
		}
	}
}

/**
 * �Զ����쳣
 */
class DivisorIsNegativeException extends Exception {
	public DivisorIsNegativeException(String msg, int divisor) {
		super(msg);
		System.out.println("error divisor:" + divisor);
	}
}
