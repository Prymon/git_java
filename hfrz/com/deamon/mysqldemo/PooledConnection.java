package com.deamon.mysqldemo;

import java.sql.Connection;

/**
 * ���ӳ�ʹ�õ�������Դ
 * @author Deamon
 */
public class PooledConnection{
	
	/**
	 * ʹ�õ����� 
	 */
	private Connection connection;
	
	/**
	 * �Ƿ���ʹ����
	 */
	private boolean isbusy;

	/**
	 * ����������connection���󹹽�һ��PooledConnection
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
