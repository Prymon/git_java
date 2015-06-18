package com.deamon.mysqldemo;

import java.util.Vector;

/**
 * 数据库连接池
 * @author Deamon
 */
public class ConnectionPool {
	private Vector<PooledConnection> connections; 
	private int connectionnums;
	private int maxconnectionnums;
	
	public static void main(String[] args) {
		
	}

}
