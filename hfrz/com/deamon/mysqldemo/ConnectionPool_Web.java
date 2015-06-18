package com.deamon.mysqldemo;

//
//    һ��Ч���ǳ�������JAVA���ݿ����ӳ�.
//    from:http://www.jxer.com/home/?uid-195-action-viewspace-itemid-332
//    ��Ȼ������APACHE COMMONS DBCP���Էǳ�����Ľ������ݿ����ӳأ�
//    ��������ƪ���°����ݿ����ӳص��ڲ�ԭ��д����ô͸����ע����ô������
//    ���Ƿǳ��ѵã��ÿ�����Ա���Ը����ε��������ݿ����ӳأ����Ƿǳ���
//    л��ƪ���µ����ߡ�
//
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import org.dom4j.DocumentException;

import com.deamon.util.XMLUtil;

public class ConnectionPool_Web {

	private String jdbcDriver = ""; // ���ݿ�����
	private String dbUrl = ""; // ���� URL
	private String dbUsername = ""; // ���ݿ��û���
	private String dbPassword = ""; // ���ݿ��û�����
	private String testTable = ""; // ���������Ƿ���õĲ��Ա�����Ĭ��û�в��Ա�
	private int initialConnections = 10; // ���ӳصĳ�ʼ��С
	private int incrementalConnections = 5; // ���ӳ��Զ����ӵĴ�С
	private int maxConnections = 50; // ���ӳ����Ĵ�С
	private Vector connections = null; // ������ӳ������ݿ����ӵ����� , ��ʼʱΪ null
	// ���д�ŵĶ���Ϊ PooledConnection ��

	public ConnectionPool_Web(String jdbcDriver, String dbUrl, String dbUsername,
			String dbPassword) {

		this.jdbcDriver = jdbcDriver;
		this.dbUrl = dbUrl;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}

	public synchronized void createPool() throws Exception {
		// ȷ�����ӳ�û�д���
		// �������ӳؼ��������ˣ��������ӵ����� connections ����Ϊ��
		if (connections != null) {
			return; // ���缺���������򷵻�
		}
		// ʵ���� JDBC Driver ��ָ����������ʵ��
		Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
		DriverManager.registerDriver(driver); // ע�� JDBC ��������
		// �����������ӵ����� , ��ʼʱ�� 0 ��Ԫ��
		connections = new Vector();
		// ���� initialConnections �����õ�ֵ���������ӡ�
		createConnections(this.initialConnections);
		System.out.println(" ���ݿ����ӳش����ɹ��� ");
	}

	private void createConnections(int numConnections) throws SQLException {
		// ѭ������ָ����Ŀ�����ݿ�����
		for (int x = 0; x < numConnections; x++) {
			// �Ƿ����ӳ��е����ݿ����ӵ����������ﵽ������ֵ�����Ա maxConnections
			// ָ�������� maxConnections Ϊ 0 ��������ʾ��������û�����ơ�
			// ���������������ﵽ��󣬼��˳���
			if (this.maxConnections > 0
					&& this.connections.size() >= this.maxConnections) {
				break;
			}
			// add a new PooledConnection object to connections vector
			// ����һ�����ӵ����ӳ��У����� connections �У�
			try {
				connections.addElement(new PooledConnection_Web(newConnection()));
			} catch (SQLException e) {
				System.out.println(" �������ݿ�����ʧ�ܣ� " + e.getMessage());
				throw new SQLException();
			}
			System.out.println(" ���ݿ����Ӽ����� ......");
		}
	}

	private Connection newConnection() throws SQLException {
		// ����һ�����ݿ�����
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername,
				dbPassword);
		// �������ǵ�һ�δ������ݿ����ӣ���������ݿ⣬��ô����ݿ��Ӧ֧�ֵ�
		// ���ͻ�������Ŀ
		// connections.size()==0 ��ʾĿǰû�����Ӽ�������
		if (connections.size() == 0) {
			DatabaseMetaData metaData = conn.getMetaData();
			int driverMaxConnections = metaData.getMaxConnections();
			// ���ݿⷵ�ص� driverMaxConnections ��Ϊ 0 ����ʾ�����ݿ�û�����
			// �������ƣ������ݿ������������Ʋ�֪��
			// driverMaxConnections Ϊ���ص�һ����������ʾ�����ݿ��Ӧ�ͻ����ӵ���Ŀ
			// �������ӳ������õ�������������������ݿ��Ӧ��������Ŀ , �������ӳص����
			// ������ĿΪ���ݿ��Ӧ�������Ŀ
			if (driverMaxConnections > 0
					&& this.maxConnections > driverMaxConnections) {
				this.maxConnections = driverMaxConnections;
			}
		}
		return conn; // ���ش������µ����ݿ�����
	}

	public synchronized Connection getConnection() throws SQLException {
		// ȷ�����ӳؼ�������
		if (connections == null) {
			return null; // ���ӳػ�û�������򷵻� null
		}
		Connection conn = getFreeConnection(); // ���һ�����õ����ݿ�����
		// ����Ŀǰû�п���ʹ�õ����ӣ������е����Ӷ���ʹ����
		while (conn == null) {
			// ��һ������
			wait(250);
			conn = getFreeConnection(); // �������ԣ�ֱ����ÿ��õ����ӣ�����
			// getFreeConnection() ���ص�Ϊ null
			// ���������һ�����Ӻ�Ҳ���ɻ�ÿ�������
		}
		return conn; // ���ػ�õĿ��õ�����
	}

	private Connection getFreeConnection() throws SQLException {
		// �����ӳ��л��һ�����õ����ݿ�����
		Connection conn = findFreeConnection();
		if (conn == null) {
			// ����Ŀǰ���ӳ���û�п��õ�����
			// ����һЩ����
			createConnections(incrementalConnections);
			// ���´ӳ��в����Ƿ��п�������
			conn = findFreeConnection();
			if (conn == null) {
				// ���紴�����Ӻ��Ի�ò������õ����ӣ��򷵻� null
				return null;
			}
		}
		return conn;
	}

	private Connection findFreeConnection() throws SQLException {
		Connection conn = null;
		PooledConnection_Web pConn = null;
		// ������ӳ����������еĶ���
		Enumeration enumerate = connections.elements();
		// �������еĶ��󣬿��Ƿ��п��õ�����
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection_Web) enumerate.nextElement();
			if (!pConn.isBusy()) {
				// ����˶���æ�������������ݿ����Ӳ�������Ϊæ
				conn = pConn.getConnection();
				pConn.setBusy(true);
				// ���Դ������Ƿ����
				if (!testConnection(conn)) {
					// ��������Ӳ��������ˣ��򴴽�һ���µ����ӣ�
					// ���滻�˲����õ����Ӷ��󣬼��紴��ʧ�ܣ����� null
					try {
						conn = newConnection();
					} catch (SQLException e) {
						System.out.println(" �������ݿ�����ʧ�ܣ� " + e.getMessage());
						return null;
					}
					pConn.setConnection(conn);
				}
				break; // �����ҵ�һ�����õ����ӣ��˳�
			}
		}
		return conn; // �����ҵ����Ŀ�������
	}

	private boolean testConnection(Connection conn) {
		try {
			// �ж����Ա��Ƿ����
			if (testTable.equals("")) {
				// ������Ա�Ϊ�գ�����ʹ�ô����ӵ� setAutoCommit() ����
				// ���ж����ӷ���ã��˷���ֻ�ڲ������ݿ���ã����粻���� ,
				// �׳��쳣����ע�أ�ʹ�ò��Ա��ķ������ɿ�
				conn.setAutoCommit(true);
			} else { // �в��Ա���ʱ��ʹ�ò��Ա�����
				// check if this connection is valid
				Statement stmt = conn.createStatement();
				stmt.execute("select count(*) from " + testTable);
			}
		} catch (SQLException e) {
			// �����׳��쳣�������Ӽ������ã��ر����������� false;
			closeConnection(conn);
			return false;
		}
		// ���ӿ��ã����� true
		return true;
	}

	public void returnConnection(Connection conn) {
		// ȷ�����ӳش��ڣ���������û�д����������ڣ���ֱ�ӷ���
		if (connections == null) {
			System.out.println(" ���ӳز����ڣ��޷����ش����ӵ����ӳ��� !");
			return;
		}
		PooledConnection_Web pConn = null;
		Enumeration enumerate = connections.elements();
		// �������ӳ��е��������ӣ��ҵ����Ҫ���ص����Ӷ���
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection_Web) enumerate.nextElement();
			// ���ҵ����ӳ��е�Ҫ���ص����Ӷ���
			if (conn == pConn.getConnection()) {
				// �ҵ��� , ���ô�����Ϊ����״̬
				pConn.setBusy(false);
				break;
			}
		}
	}

	public synchronized void refreshConnections() throws SQLException {
		// ȷ�����ӳؼ����´���
		if (connections == null) {
			System.out.println(" ���ӳز����ڣ��޷�ˢ�� !");
			return;
		}
		PooledConnection_Web pConn = null;
		Enumeration enumerate = connections.elements();
		while (enumerate.hasMoreElements()) {
			// ���һ�����Ӷ���
			pConn = (PooledConnection_Web) enumerate.nextElement();
			// �������æ��� 5 �� ,5 ���ֱ��ˢ��
			if (pConn.isBusy()) {
				wait(5000); // �� 5 ��
			}
			// �رմ����ӣ���һ���µ����Ӵ�������
			closeConnection(pConn.getConnection());
			pConn.setConnection(newConnection());
			pConn.setBusy(false);
		}
	}

	public synchronized void closeConnectionPool() throws SQLException {
		// ȷ�����ӳش��ڣ����粻���ڣ�����
		if (connections == null) {
			System.out.println(" ���ӳز����ڣ��޷��ر� !");
			return;
		}
		PooledConnection_Web pConn = null;
		Enumeration enumerate = connections.elements();
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection_Web) enumerate.nextElement();
			// ����æ���� 5 ��
			if (pConn.isBusy()) {
				wait(5000); // �� 5 ��
			}
			// 5 ���ֱ�ӹر���
			closeConnection(pConn.getConnection());
			// �����ӳ�������ɾ����
			connections.removeElement(pConn);
		}
		// �����ӳ�Ϊ��
		connections = null;
	}

	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(" �ر����ݿ����ӳ����� " + e.getMessage());
		}
	}

	private void wait(int mSeconds) {
		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e) {
		}
	}

	public int getInitialConnections() {
		return this.initialConnections;
	}

	public void setInitialConnections(int initialConnections) {
		this.initialConnections = initialConnections;
	}

	public int getIncrementalConnections() {
		return this.incrementalConnections;
	}

	public void setIncrementalConnections(int incrementalConnections) {
		this.incrementalConnections = incrementalConnections;
	}

	public int getMaxConnections() {
		return this.maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public String getTestTable() {
		return this.testTable;
	}

	public void setTestTable(String testTable) {
		this.testTable = testTable;
	}
	
	public static void main(String[] args) throws DocumentException {
		Map<String, String> confs = XMLUtil.getNodeContentMaps("mysql", "conf/config.xml");
		String dbdriver = confs.get("dbdriver");
		String user = confs.get("user");
		String password = confs.get("password");
		String url = confs.get("url");
		ConnectionPool_Web connPool = new ConnectionPool_Web(dbdriver, url,user,password);

		try {
			connPool.createPool();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			Connection conn = connPool.getConnection();
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		}

	}
}

class PooledConnection_Web {
	Connection connection = null; // ���ݿ�����
	boolean busy = false; // �������Ƿ�����ʹ�õı�־��Ĭ��û������ʹ��

	// ���캯��������һ�� Connection ����һ�� PooledConnection ����
	public PooledConnection_Web(Connection connection) {
		this.connection = connection;
	}

	// ���ش˶����е�����
	public Connection getConnection() {
		return connection;
	}

	// ���ô˶���ģ�����
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// ��ö��������Ƿ�æ
	public boolean isBusy() {
		return busy;
	}

	// ���ö������������æ
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
}