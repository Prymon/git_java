package com.deamon.mysql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.DocumentException;
import org.junit.Test;

import com.deamon.util.XMLUtil;

/**
 * 连接池接口的mysql实现 mysql连接池
 * 基于同步的ConcurrentHashMap的实现
 * getConnectionFromPool()和ReturnConnection(Connection)使用了两个不同的同步锁
 * @author Deamon
 */
public final class MysqlConnectionPool implements ConnectionPool{

	Logger logger = Logger.getLogger(MysqlConnectionPool.class);
	public static final String DBDRIVER = "com.mysql.jdbc.Driver"; // mysql的驱动
	public static final int ONRELEASETIMEINTERVAL = 2000; // 释放连接池等待连接关闭时间
	private String url; // 连接url
	private String user; // 数据库用户名
	private String passwd; // 数据库密码
	private int numOfConnection; // 连接池中连接数量
	private int maxNumOfConnection = 100; // 连接池最大连接数量
	private int maxBlockedTime = 20000; // 获取连接最大等待时间
	private int singleWaitSec = 200; // 获取连接单次等待时间

	private Map<Connection, Integer> connMap; // <连接,连接状态>。-1为异常状态、0为已连接未使用、1为已连接正使用

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
		logger.info("start query max num of connection");
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
			//创建一个同步的ConcurrentHashMap
//			connMap = Collections.synchronizedMap(new HashMap<Connection, Integer>());	//方案1
			connMap = new ConcurrentHashMap<Connection, Integer>();
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
		Connection freeConn = getFreeConnection();
		int connGetTimeInterval = 0;
		while (freeConn == null && connGetTimeInterval < maxBlockedTime) {
			waitMiles(singleWaitSec);
			connGetTimeInterval += singleWaitSec;
			freeConn = getFreeConnection();
			System.out.println("beening waiting for " + connGetTimeInterval
					+ "miles");
		}
		if(freeConn!=null)
			freeConn = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, new ProxyConn(freeConn));
		return freeConn;
	}

	@Override
	public boolean ReturnConnection(Connection conn) {
		synchronized(MysqlConnectionPool.class){
			if (connMap == null)
				return false;
			if (connMap.get(conn) != null) {
				connMap.put(conn, 0);
				return true;
			}
			return false;
		}

	}

	@Override
	public boolean releaseConnectionPool() {
		if (connMap == null)
			return false;
		for (Entry<Connection, Integer> connEntry : connMap.entrySet()) {
			if (connEntry.getValue() == 1) // 连接还在占用，等待2s
				waitMiles(ONRELEASETIMEINTERVAL);
			try {
				connEntry.getKey().close();
				// connMap.remove(connEntry.getKey());
			} catch (SQLException e) {
				logger.error(" 关闭数据库连接出错： " + e.getMessage());
				return false;
			}
		}
		connMap = null;
		return true;
	}

	public int getNumOfConnection() {
		return numOfConnection;
	}

	public int getMaxNumOfConnection() {
		return maxNumOfConnection;
	}

	public int getMaxBlockedTime() {
		return maxBlockedTime;
	}

	/**
	 * 设置获取连接最大等待时间（ms）,设置为-1则为无限制
	 * 
	 * @param maxBlockedTime
	 */
	public void setMaxBlockedTime(int maxBlockedTime) {
		this.maxBlockedTime = maxBlockedTime < 0 ? -1 : maxBlockedTime;
	}

	public int getSingleWaitSec() {
		return singleWaitSec;
	}

	/**
	 * 设置获取连接时，单次等待时间（ms） 范围[10,10000],参数溢出则保持不变
	 * 
	 * @param singleWaitSec
	 */
	public void setSingleWaitSec(int singleWaitSec) {
		this.singleWaitSec = (singleWaitSec >= 10 && singleWaitSec <= 10000) ? singleWaitSec
				: this.singleWaitSec;
	}

	private void waitMiles(int miles) {
		try {
			Thread.sleep(miles);
		} catch (InterruptedException e) {
			logger.error("wait for connection close error");
			e.printStackTrace();
		}
	}

	/**
	 * 获得空闲的连接。若无返回null
	 * 
	 * @return
	 */
	private Connection getFreeConnection() {
		for (Entry<Connection, Integer> connEntry : connMap.entrySet()) {
			if (connEntry.getValue() == 0) {
				connEntry.setValue(1);
				return connEntry.getKey();
			}
		}
		return null;
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
		pool.setMaxBlockedTime(8000);
		pool.setSingleWaitSec(20);
		pool.init(5, url, user, password);

		for (int i = 0; i < 20; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Connection conn =pool.getConnectionFromPool();
					if (null == conn) {
						System.out.println("得不到连接");
					} else {
						System.out.println("得到连接");
					}
//					waitMiles(2000);
					System.out.println("return"+pool.ReturnConnection(conn));
				}
			}).start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("尝试释放");
		if (pool.releaseConnectionPool()) {
			System.out.println("释放完成");
		}
		System.out.println(pool.init(5, url, user, password)?"init scuess":"failed");

		Connection conn = pool.getConnectionFromPool();
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("select * from carvin limit 100");
		while (res.next()) {
			int count = res.getMetaData().getColumnCount();
			for (int i = 1; i <= count; i++)
				System.out.print(res.getString(i) + "--");
			System.out.println();
		}
		res.last();
		System.out.println(res.getRow());
		conn.close();
	}
	
	//测试connection的关闭，和pool的关闭对于资源的释放
	@Test
	public void test2() throws DocumentException, SQLException{
		PropertyConfigurator.configure("conf/log4j.properties");
		Map<String, String> confs = XMLUtil.getNodeContentMaps("mysql",
				"conf/config.xml");
		String dbdriver = confs.get("dbdriver");
		String user = confs.get("user");
		String password = confs.get("password");
		String url = confs.get("url");
		System.out.println(url);
		final MysqlConnectionPool pool = new MysqlConnectionPool();
		pool.setMaxBlockedTime(8000);
		pool.setSingleWaitSec(20);
		pool.init(5, url, user, password);
		Connection conn1 = pool.getConnectionFromPool();
		conn1.close();
		System.out.println("conn close: "+conn1.isClosed());
		pool.releaseConnectionPool();
		System.out.println("conn close: "+conn1.isClosed());
		
	}
	/**
	 * 动态代理Handler内部类
	 * 处理connection的close()请求 
	 * @author Deamon
	 */
	class ProxyConn implements InvocationHandler{
		Object proxyObj;
		
		public ProxyConn(Object proxyObj){
			this.proxyObj = proxyObj;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if(method.getName().equals("close")){
				ReturnConnection((Connection) this.proxyObj);
				return null;
			}
			return method.invoke(this.proxyObj, args);
		}
	}
}