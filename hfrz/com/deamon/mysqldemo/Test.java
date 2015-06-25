package com.deamon.mysqldemo;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.dom4j.DocumentException;

import com.vinana.util.XMLUtil;

public class Test {
	private static DataSource ds = null;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileReader(XMLUtil.getUserPath("conf/dbcpconfig.properties")));
//		ds = new BasicDataSourceFactory().;
		
		
	}
	


	
	@org.junit.Test
	public void test1() {
		try {
			Map<String, String> confs = XMLUtil.getNodeContentMaps("mysql", "conf/config.xml");
			String dbdriver = confs.get("dbdriver");
			String user = confs.get("user");
			String password = confs.get("password");
			String url = confs.get("url");
			Class.forName(dbdriver);
			Connection conn = DriverManager.getConnection(url, user, password);
			int maxnumconn = conn.getMetaData().getMaxConnections();
			System.out.println(maxnumconn);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void test2() throws DocumentException, ClassNotFoundException, SQLException{
		Map<String, String> confs = XMLUtil.getNodeContentMaps("mysql", "conf/config.xml");
		String dbdriver = confs.get("dbdriver");
		String user = confs.get("user");
		String password = confs.get("password");
		String url = confs.get("url");
		Class.forName(dbdriver);
		Connection conn_1 = DriverManager.getConnection(url, user, password);
		Connection conn_2 = DriverManager.getConnection(url, user, password);
		System.out.println(conn_1.hashCode()+"||"+conn_2.hashCode());
	}
}
