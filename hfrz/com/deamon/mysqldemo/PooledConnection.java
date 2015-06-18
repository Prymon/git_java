package com.deamon.mysqldemo;

import java.sql.Connection;

/**
 * 连接池使用的连接资源
 * @author Deamon
 */
public class PooledConnection{
	
	/**
	 * 使用的连接 
	 */
	private Connection connection;
	
	/**
	 * 是否在使用中
	 */
	private boolean isbusy;

	/**
	 * 根据所给的connection对象构建一个PooledConnection
	 * @param conn
	 */
	public PooledConnection(Connection conn){
		this.connection = conn;
	}

	public boolean isBusy() {
		return isbusy;
	}
	
	public void setBusy(boolean isbusy) {
		this.isbusy = isbusy;
	}
	
}
