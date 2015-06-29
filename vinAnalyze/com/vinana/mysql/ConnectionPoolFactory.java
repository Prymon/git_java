package com.vinana.mysql;

import java.util.Map;

import org.dom4j.DocumentException;

import com.vinana.util.XMLUtil;

/**
 * »ñµÃµ¥ÀýConnPool
 * 
 * @author Deamon
 */
public class ConnectionPoolFactory {
	private static MysqlConnectionPool SingleConnPool;
	static {
		SingleConnPool = new MysqlConnectionPool();
		Map<String, String> confs;
		try {
			confs = XMLUtil.getNodeContentMaps("mysql", "conf/config.xml");
			String dbdriver = confs.get("dbdriver");
			String user = confs.get("user");
			String password = confs.get("password");
			String url = confs.get("url");
			SingleConnPool.init(1, url, user, password);
		} catch (DocumentException e) {
			e.printStackTrace();
			SingleConnPool = null;
		}
	}

	private ConnectionPoolFactory() {
	}

	public static ConnectionPool getMysqlConnectionPool() {
		return SingleConnPool;
	}
}
