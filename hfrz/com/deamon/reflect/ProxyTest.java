package com.deamon.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) throws Exception {
		LogProxy dp = new LogProxy(new Implement());
		Foo proxy = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
				new Class[] { Foo.class }, dp);
		proxy.f("proxy");
	}
}

/**
 * �����ӿ�
 * @author Deamon
 */
interface Foo {
	void f(String s);
}

/**
 * �ӿ�ʵ����
 * @author Deamon
 */
class Implement implements Foo {

	@Override
	public void f(String s) {
		System.out.println(s);
	}
}

/**
 * InvocationHandler �Ǵ���ʵ��ĵ��ô������ ʵ�ֵĽӿڡ� 
 * @author Deamon
 */
class LogProxy implements InvocationHandler {
	public Object obj = null;

	public LogProxy(Object object) {
		obj = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		System.out.println("call " + methodName + " begin ");
		Object result = method.invoke(obj, args);
		System.out.println("call " + methodName + " end");
		return result;
	}

}