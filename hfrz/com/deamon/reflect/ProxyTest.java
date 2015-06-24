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
 * 欲代理接口
 * @author Deamon
 */
interface Foo {
	void f(String s);
}

/**
 * 接口实现类
 * @author Deamon
 */
class Implement implements Foo {

	@Override
	public void f(String s) {
		System.out.println(s);
	}
}

/**
 * InvocationHandler 是代理实例的调用处理程序 实现的接口。 
 * @author Deamon
 */
class LogProxy implements InvocationHandler {
	public Object obj = null;

	public LogProxy(Object object) {
		obj = object;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		System.out.println("call " + methodName + " begin ");
		Object result = method.invoke(obj, args);
		System.out.println("call " + methodName + " end");
		return result;
	}

}