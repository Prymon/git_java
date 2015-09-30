package exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 1.finallyͨ�������ͷ���Դ����ر����ݿ����� 2.finallyֻ��һ���������ִ�С��������� System.exit(args);
 */
public class ExceptionFinally {
	@Test // ������ʹ��finally�ĳ������ر����ݿ�����
	public void test1() {
		try {
			JdbcUtil.getConnection();
			System.out.println("ʹ�����ݿ�����");
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			JdbcUtil.release();
			System.out.println("�Ͽ����ݿ�����");
		}
	}

	@Test // try catch�ļ�����ʽ
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

	// �õ����ݿ�����
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	// �ͷ����ݿ�����
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