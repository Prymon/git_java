package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaisicJdbc {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncode=utf-8";
	private static final String username = "root";
	private static final String password = "root";

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.prepareStatement("select * from test where id = ?");
			stat.setString(1, "1");
			result = stat.executeQuery();
			while(result!=null && result.next()){
				System.out.print(result.getString(1)+"   ");
				System.out.println(result.getString(2));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}
