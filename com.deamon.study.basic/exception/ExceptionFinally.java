package exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 1.finally通常用于释放资源，如关闭数据库连接 2.finally只有一种情况不会执行。当遇到： System.exit(args);
 */
public class ExceptionFinally {
	@Test // 常见的使用finally的场景，关闭数据库连接
	public void test1() {
		try {
			JdbcUtil.getConnection();
			System.out.println("使用数据库连接");
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			JdbcUtil.release();
			System.out.println("断开数据库连接");
		}
	}

	@Test // try catch的几种形式
	public void test2() {
		// ----------------------
		try {

		} catch (Exception e) {

		} finally {

		}
		// ----------------------
		try {

		} catch (Exception e) {

		}
		// ----------------------
		try {

		} finally {

		}
	}
}

class JdbcUtil {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/library";
	private static String username = "root";
	private static String password = "root";
	private static Connection conn = null;

	// 得到数据库连接
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	// 释放数据库连接
	public static void release() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}