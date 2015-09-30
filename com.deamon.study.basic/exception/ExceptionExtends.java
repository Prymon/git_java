package exception;

/**
 * 1.�����ڼ̳и���ʱ���������ķ����׳��쳣����ô����ĸ��Ƿ�����ֻ���׳�������쳣����쳣������
 * 2.������෽���׳�����쳣�������ڸ��Ǹ÷���ʱ��ֻ���׳������쳣���Ӽ� 3.�������ķ�����ӿ���û���쳣�׳�����ô����ĸ��Ƿ���ʱ��Ҳ�������׳��쳣
 */
public class ExceptionExtends {
	public static void main(String[] args) {
		// try {
		// AA b = new BB();
		// b.print();
		// } catch (MyExcep1 e) {
		// e.printStackTrace();
		// }
		BB b = new BB();
		b.print();
	}
}

class AA {
	public void print() throws MyExcep1 {
		System.out.println("clasa A");
		throw new MyExcep1();
	}
}

/**
 * �˴����ĳ��׳�AnotherExcep����������
 */
/*
 * ����˶� class BB extends AA{
 * 
 * public void print() throws AnotherExcep{ System.out.println("class B"); throw
 * new AnotherExcep(); } }
 */
class BB extends AA {

	public void print() {
		System.out.println("class B");
	}
}

// ���쳣
class MyExcep1 extends Exception {

}

// ���쳣
class MyExcep2 extends MyExcep1 {

}

// �������쳣
class AnotherExcep extends Exception {

}
