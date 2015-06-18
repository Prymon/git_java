package com.deamon.mysql;

import java.sql.Connection;

/**
 * ���ݿ����ӳ�
 * 
 * @author Deamon
 */
public interface ConnectionPool {

	/**
	 * ���ӳ�Ĭ����������
	 */
	public static final int DEFAULTNUMOFCONNECTION = 10;

	/**
	 * ��Ĭ��������ʼ�����ӳ�
	 * 
	 * @param url
	 * @param user
	 * @param passwd
	 * @return
	 */
	public boolean init(String url, String user, String passwd);

	/**
	 * ��ʼ�����ӳ�
	 * 
	 * @param numOfConn
	 *            ��������
	 * @param url
	 * @param user
	 * @param passwd
	 * @return ��ʼ���Ƿ�ɹ�
	 */
	public boolean init(int numOfConn, String url, String user, String passwd);

	/**
	 * �����ӳ�ȡ������
	 * 
	 * @return
	 */
	public Connection getConnectionFromPool();

	/**
	 * �����ӻ������ӳ�
	 * 
	 * @param conn
	 * @return �����Ƿ��������ӳ�
	 */
	public boolean ReturnConnection(Connection conn);

	/**
	 * �ͷ���Դ���ر����ӳ�
	 * 
	 * @return
	 */
	public boolean releaseConnectionPool();

}
