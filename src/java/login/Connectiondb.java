package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connectiondb {
	private static String sql = "select * from login where uname=? and pass=?";
	private static String url = "jdbc:mysql://localhost:3306/course_management";
	private static String uname = "root";
	private static String pass = "nijith";
	public static Connection con = null;

	public static Connection getCon() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
