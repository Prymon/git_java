package com.deamon.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) throws Exception {
//		LogProxy dp = new LogProxy(new Implement());
//		Foo proxy = 
//				(Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),new Class[] { Foo.class }, dp);
//		proxy.f("proxy");
		ss s = new ss();
		System.out.println(s.num);
		s.i();
		System.out.println(s.num);
	}
}

interface Foo {
	void f(String s);
}

class Implement implements Foo {

	@Override
	public void f(String s) {
		System.out.println(s);
	}

}

class LogProxy implements InvocationHandler {
	public LogProxy(Object object) {
		obj = object;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		System.out.println("call " + methodName + "begin ");
		Object result = method.invoke(obj, args);
		System.out.println("call " + methodName + "end");
		return result;
	}

	public Object obj = null;
}

class ss{
	int num=2;
	
	void i() throws Exception{
		t(new Object(){
			public String toString(){
				num = 10;
				return num+"";
			}
		});
		System.out.println("2");
	}
	void t(Object obj){
		obj.toString();
		throw new RuntimeException("1");
	}
}


