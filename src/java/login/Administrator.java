package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrator {
	public boolean deleteCourse(String courseId) throws SQLException {
		String sql = "delete from course_list where course_id=?";
		Connection con = Connectiondb.getCon();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, courseId);
		int result = ps.executeUpdate();
		if (result != 0) {
			return true;
		}
		return false;
	}

	public boolean student(String id, String name, String sem) throws SQLException {
		try {
			int studentId = Integer.parseInt(id);
			int semester = Integer.parseInt(sem);
			String sql = "insert into student_details values(?,?,?)";
			Connection con = Connectiondb.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setString(2, name);
			ps.setInt(3, semester);
			int rs = ps.executeUpdate();
			if (rs != 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean adminDb(String name, String password) throws SQLException {
		String sql = "select * from admindatabase";
		Connection con = Connectiondb.getCon();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		String dbname = "";
		String dbpassword = "";
		while (rs.next()) {
			dbname = rs.getString(1);
			dbpassword = rs.getString(2);
			if (dbname.equals(name) && dbpassword.equals(password)) {
				return true;
			}
		}

		return false;
	}

	public boolean removeStudent(String id) throws SQLException {
		String sql = "delete from student_details where s_id=?";
		Connection con = Connectiondb.getCon();
		PreparedStatement ps = con.prepareStatement(sql);
		int sId = Integer.parseInt(id);
		ps.setInt(1, sId);
		int result = ps.executeUpdate();
		if (result != 0) {
			return true;
		}
		return false;

	}

	public boolean addCourse(String courseId, String courseName) throws SQLException {
		try {
			String sql = "insert into course_list values(?,?)";
			Connection con = Connectiondb.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2, courseName);
			int result = ps.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	
}
