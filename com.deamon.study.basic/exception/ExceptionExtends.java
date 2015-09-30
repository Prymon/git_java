package exception;

/**
 * 1.子类在继承父类时，如果父类的方法抛出异常，那么子类的覆盖方法，只能抛出父类的异常或该异常的子类
 * 2.如果父类方法抛出多个异常，子类在覆盖该方法时，只能抛出父类异常的子集 3.如果父类的方法或接口中没有异常抛出，那么子类的覆盖方法时，也不可以抛出异常
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
 * 此处若改成抛出AnotherExcep，则编译错误
 */
/*
 * 例如此段 class BB extends AA{
 * 
 * public void print() throws AnotherExcep{ System.out.println("class B"); throw
 * new AnotherExcep(); } }
 */
class BB extends AA {

	public void print() {
		System.out.println("class B");
	}
}

// 父异常
class MyExcep1 extends Exception {

}

// 子异常
class MyExcep2 extends MyExcep1 {

}

// 第三方异常
class AnotherExcep extends Exception {

}
