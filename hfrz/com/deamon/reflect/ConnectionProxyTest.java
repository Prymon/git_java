package com.deamon.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionProxyTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new ConnectionProxyTest().test1();

	}

	public void test1() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://192.168.1.52:3306/test?characterEncoding=utf-8",
				"hfrzmysql", "123456");
		conn = (Connection) Proxy.newProxyInstance(conn
				.getClass().getClassLoader(), new Class[]{Connection.class},
				new ConnProxy(conn));
		conn.close();
		System.out.println(conn.isClosed());
		System.out.println(conn.isClosed());
	}
}

class ConnProxy implements InvocationHandler {
	private Object proxyObj;

	public ConnProxy(Object proxyObj) {
		this.proxyObj = proxyObj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(method.getName().equals("close")){
			System.out.println("close");
			method.invoke(proxyObj, args); 
			return null;
		}
		return method.invoke(proxyObj, args); 
	}

}
