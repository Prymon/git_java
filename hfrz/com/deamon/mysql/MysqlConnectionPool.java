package com.deamon.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.DocumentException;
import org.junit.Test;

import com.deamon.util.XMLUtil;

/**
 * 连接池接口的mysql实现 mysql连接池
 * 
 * @author Deamon
 */
public class MysqlConnectionPool implements ConnectionPool {

	Logger logger = Logger.getLogger(MysqlConnectionPool.class);
	public static final String DBDRIVER = "com.mysql.jdbc.Driver"; // mysql的驱动
	private String url; // 连接url
	private String user; // 数据库用户名
	private String passwd; // 数据库密码
	private int numOfConnection; // 连接池中连接数量
	private int maxNumOfConnection = 100; // 连接池最大连接数量
	private Map<Connection, Integer> connMap; // <连接,连接状态>。-1为异常状态、0为已连接未使用、1为已连接正使用
	private String string;

	public MysqlConnectionPool() {
	}

	@Override
	public boolean init(String url, String user, String passwd) {
		return init(DEFAULTNUMOFCONNECTION, url, user, passwd);
	}

	@Override
	public boolean init(int numOfConn, String url, String user, String passwd) {
		if (null != connMap) // 重复初始化了
			return false;
		this.numOfConnection = numOfConn < maxNumOfConnection ? numOfConn
				: maxNumOfConnection;
		this.url = url;
		this.user = user;
		this.passwd = passwd;
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("ConnectionPool init mysql driver" + DBDRIVER
					+ "failed : " + e.getMessage());
			return false;
		}
		try {
			// 修正数据库最大连接数
			int maxNumOfConnection = DriverManager
					.getConnection(url, user, passwd).getMetaData()
					.getMaxConnections();
			if (maxNumOfConnection > 0
					&& maxNumOfConnection < this.maxNumOfConnection)
				this.maxNumOfConnection = maxNumOfConnection;
			System.out.println("max num correct scuess");
			// 初始化连接池
			connMap = new HashMap<Connection, Integer>();
			for (int i = 0; i < numOfConnection; i++) {
				Connection conn = DriverManager
						.getConnection(url, user, passwd);
				connMap.put(conn, 0);
			}
			logger.info("init " + numOfConnection + "connection scuess");
		} catch (SQLException e) {
			connMap = null;
			e.printStackTrace();
			logger.error("Connect to database failed");
			return false;
		}
		return true;
	}

	@Override
	public synchronized Connection getConnectionFromPool() {
		if (connMap == null)
			return null;
		for (Entry<Connection, Integer> connEntry : connMap.entrySet()) {
			if (connEntry.getValue() == 0) {
				connEntry.setValue(1);
				return connEntry.getKey();
			}
		}
		return null;
	}

	@Override
	public synchronized boolean ReturnConnection(Connection conn) {
		if (connMap == null)
			return false;
		if (connMap.get(conn) != null) {
			connMap.put(conn, 0);
			return true;
		}
		return false;
	}

	@Override
	public boolean releaseConnectionPool() {
		if (connMap == null)
			return false;
		for (Entry<Connection, Integer> connEntry : connMap.entrySet()) {
			if (connEntry.getValue() == 1) // 连接还在占用，等待5s
				waitSecond(5);
			try {
				connEntry.getKey().close();
			} catch (SQLException e) {
				logger.error(" 关闭数据库连接出错： " + e.getMessage());
				return false;
			}
		}
		return true;
	}

	public int getNumOfConnection() {
		return numOfConnection;
	}

	public int getMaxNumOfConnection() {
		return maxNumOfConnection;
	}

	private void waitSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			logger.error("wait for connection close error");
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws DocumentException, SQLException {
		PropertyConfigurator.configure("conf/log4j.properties");
		Map<String, String> confs = XMLUtil.getNodeContentMaps("mysql",
				"conf/config.xml");
		String dbdriver = confs.get("dbdriver");
		String user = confs.get("user");
		String password = confs.get("password");
		String url = confs.get("url");
		System.out.println(url);
		final MysqlConnectionPool pool = new MysqlConnectionPool();
		pool.init(5,url, user, password);

		for(int i=0;i<10;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(null != pool.getConnectionFromPool()){
						System.out.println("得不到连接");
					}
				}
			}).start();
		}
		System.out.println("尝试释放");
		System.out.println(pool.releaseConnectionPool());
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*Connection conn = pool.getConnectionFromPool();
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("select * from carvin limit 10");
		while (res.next()) {
			int count = res.getMetaData().getColumnCount();
			for (int i = 1; i <= count; i++)
				System.out.print(res.getString(i) + "--");
			System.out.println();
		}*/

	}

}
