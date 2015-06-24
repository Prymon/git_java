package com.deamon.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.DocumentException;

import com.deamon.mysql.MysqlConnectionPool;
import com.deamon.util.XMLUtil;

public class TestMysql {

	public static void main(String[] args) throws InterruptedException {
		long timeinterval = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			new writeThr().start();
		}
		while(writeThr.count!=1000){
			Thread.sleep(200l);
		}
		timeinterval = System.currentTimeMillis()-timeinterval;
		System.out.println(timeinterval);
	}
}
class writeThr extends Thread{
	public static int count = 0; 
	static MysqlConnectionPool pool;
	static{
		PropertyConfigurator.configure("conf/log4j.properties");
		Map<String, String> confs;
		try {
			confs = XMLUtil.getNodeContentMaps("mysql",
					"conf/config.xml");
			String dbdriver = confs.get("dbdriver");
			String user = confs.get("user");
			String password = confs.get("password");
			String url = confs.get("url");
			System.out.println(url);
			pool  = new MysqlConnectionPool();
			pool.setMaxBlockedTime(8000);
			pool.setSingleWaitSec(20);
			pool.init(5, url, user, password);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	private static int interval = 50;
	private static String sql1 = "insert into table1 value(";
	private static String sql2 = ");";
	public void run(){
		Connection conn = pool.getConnectionFromPool();
		try {
			Statement state = conn.createStatement();
			String sql = sql1+"null,"+new Random().nextInt(10000)+","+new Random().nextInt(10000)+sql2;
			System.out.println(sql);
			state.executeUpdate(sql);
			state.close();
			conn.close();
			count++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}







