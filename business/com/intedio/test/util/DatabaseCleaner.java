package com.intedio.test.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.intedio.test.mysql.ConnectionPool;
import com.intedio.test.mysql.MysqlConnectionPool;

/**
 * 可以清空数据表，用作测试
 * 
 * @author dimen
 */
public class DatabaseCleaner {

	private static ConnectionPool pool;

	public static void main(String[] args) throws SQLException {
		Init();
		select();
	}
	
	private static void Init() {
		String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8"; // 连接url
		String user = "root"; // 数据库用户名
		String passwd = "root"; // 数据库密码
		pool = new MysqlConnectionPool();
		pool.init(5, url, user, passwd);
	}

	private static void select() throws SQLException{
		String sql1 = "select carplate from oneplatemulticars group by CarPlate having count(distinct SF_CarColorCode,SF_CarKindCode)>1";
//		sql1 = "select * from oneplatemulticars";
		Connection conn = pool.getConnectionFromPool();
		java.sql.Statement Statement = conn.createStatement();
		ResultSet result = Statement.executeQuery(sql1);
		while(result.next()){
			System.out.println(result.getString("carplate"));
		}
		
	}
	
	private static void clean() {
		try {
			String sql1 = "truncate table vidtaskmap;";
			String sql2 = "truncate table oneplatemulticars;";
			String sql3 = "truncate table policecar;";
			String sql4 = "truncate table tollgatecarwarninfo;";
			String sql5 = "truncate table vidtaskmap;";
			Connection conn = pool.getConnectionFromPool();
			java.sql.Statement Statement = conn.createStatement();
			Statement.addBatch(sql1);
			Statement.addBatch(sql2);
			Statement.addBatch(sql3);
			Statement.addBatch(sql4);
			Statement.addBatch(sql5);
			int[] executeBatch = Statement.executeBatch();
			System.out.println(executeBatch[0]);
			Statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
