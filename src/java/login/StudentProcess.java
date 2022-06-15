package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;


public class StudentProcess {

	public  JSONObject studentList() {
		String sql = "select s_id,s_name from student_details";
		JSONObject json = new JSONObject();
		try {
			Connection con = Connectiondb.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				json.put(rs.getInt(1) + "", rs.getString(2));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return json;
	}

	public  JSONObject courseList() {
		String sql = "select * from course_list";
		JSONObject json = new JSONObject();
		try {
			Connection con = Connectiondb.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				json.put(rs.getString(1), rs.getString(2));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return json;
	}

	public boolean userDb(String studentId, String studentName) throws SQLException {
		final String sql = "select s_id,s_name from student_details";
		Connection con = Connectiondb.getCon();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int dbId = 0;
		String dbName = "";
		int studentId1 = Integer.parseInt(studentId);
		while (rs.next()) {
			dbId = rs.getInt(1);
			dbName = rs.getString(2);
			if (studentId1 == dbId && studentName.equals(dbName)) {
				return true;
			}
		}
		return false;
	}

	public  JSONObject studentCourse(String studentid) {
		int id = Integer.parseInt(studentid);
		final String sql = "select course_id,course_name from course_list where course_id in(select courseid from enroll where studentid=?)";
		JSONObject json = new JSONObject();
		try {
			Connection con = Connectiondb.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				json.put(rs.getString(1), rs.getString(2));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return json;
	}

	public JSONObject coursedetails(String courseid) {
		final String sql = "select s_id,s_name from student_details where s_id in ( select studentid from enroll where courseid=?);";
		JSONObject json = new JSONObject();
		try {
			Connection con = Connectiondb.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, courseid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				json.put(rs.getString(1), rs.getString(2));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return json;
	}

	public  int enRollCourse(String studentId, String courseName) throws SQLException {
		final String sql = "insert into enroll values(?,?)";
		try {
			Connection con = Connectiondb.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			int id = Integer.parseInt(studentId);
			ps.setInt(1, id);
			ps.setString(2, courseName);
			int rs = ps.executeUpdate();
			if (rs != 0) {
				return 1;
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				return 2;
			} else if (e.getMessage().equals(
					"Cannot add or update a child row: a foreign key constraint fails (`course_management`.`enroll`, CONSTRAINT `enroll_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `course_list` (`course_id`) ON DELETE CASCADE)")) {
				return 3;
			}
		}
		return 0;
	}
}
