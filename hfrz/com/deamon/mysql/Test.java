package com.deamon.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.dom4j.DocumentException;

import com.deamon.util.XMLUtil;
import com.mysql.jdbc.DatabaseMetaData;

public class Test {
	public static void main(String[] args) {
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
	
}
