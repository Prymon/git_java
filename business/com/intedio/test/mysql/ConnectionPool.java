package com.intedio.test.mysql;

import java.sql.Connection;



/**
 * 数据库连接池
 * 
 * @author Deamon
 */
public interface ConnectionPool {

	/**
	 * 连接池默认连接数量
	 */
	public static final int DEFAULTNUMOFCONNECTION = 10;

	/**
	 * 以默认数量初始化连接池
	 * 
	 * @param url
	 * @param user
	 * @param passwd
	 * @return
	 */
	public boolean init(String url, String user, String passwd);

	/**
	 * 初始化连接池
	 * 
	 * @param numOfConn
	 *            连接数量
	 * @param url
	 * @param user
	 * @param passwd
	 * @return 初始化是否成功
	 */
	public boolean init(int numOfConn, String url, String user, String passwd);

	/**
	 * 从连接池取出连接
	 * 
	 * @return
	 */
	public Connection getConnectionFromPool();

	/**
	 * 把连接还给连接池
	 * 
	 * @param conn
	 * @return 连接是否属于连接池
	 */
	public boolean ReturnConnection(Connection conn);

	/**
	 * 释放资源，关闭连接池
	 * 
	 * @return
	 */
	public boolean releaseConnectionPool();

}
