package login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;

import org.json.JSONObject;

@WebServlet("/Myservlet")
public class AdministratorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			out.print("<body><h3 id='delete'>Please login First!</h3><script src='validate.js'></script></body>");
            
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.include(request, response);
		} else {
			Administrator admin = new Administrator();
			StudentProcess p = new StudentProcess();

			// Enroll student
			String studentId = request.getParameter("sid");
			String studentName = request.getParameter("sname");
			String studentSem = request.getParameter("ssem");
			String studentSubmit = request.getParameter("addstudentsubmit");
			if (studentSubmit != null) {
				try {
					if (admin.student(studentId, studentName, studentSem)) {
						out.print(
								"<body><h3 id='enroll'>Student has been Added Successfully!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);
                                                  response.getWriter().write("success");

					} else {
						out.print(
								"<body><h3 id='delete'>Oops Student Already Present!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);

					}
					// out.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// Delete student
			String deletesid = request.getParameter("deletesid");
			String deletesubmit = request.getParameter("removestudentsubmit");
			if (deletesubmit != null) {
				try {
					if (admin.removeStudent(deletesid)) {

						out.print("<body><h3 id='enroll'>Student has been removed successfully</h3><script src='validate.js'></script></body>");
                                                 
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);
                                                    
					} else {

						out.print(
								"<body><h3 id='delete'>Oops Student not present!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// Add course
			String courseId = request.getParameter("addcourse_id");
			String courseName = request.getParameter("addcourse_name");
			String courseSubmit = request.getParameter("addcourse");
			if (courseSubmit != null) {
				try {
					if (admin.addCourse(courseId, courseName)) {
						out.print(
								"<body><h3 id='enroll'>Course Has Been Added Successfully!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);
					} else {
						out.print(
								"<body><h3 id='delete'>Oops Course Already Present!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// Delete course
			String deleteCourseId = request.getParameter("deletecourse");
			String deleteCourseSubmit = request.getParameter("removecourse");
			if (deleteCourseSubmit != null) {
				try {
					if (admin.deleteCourse(deleteCourseId)) {
						out.print(
								"<body><h3 id='enroll'>Course has been removed successfully!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);
					} else {
						out.print(
								"<body><h3 id='delete'>Oops course not found!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
						rd.include(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// List of course
			String listCourse = request.getParameter("tableCourse");
			if (listCourse != null) {
				JSONObject str = p.courseList();
				Iterator<String> keys = str.keys();
				out.println("<html><head></head><body>");
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<th>Course Id </th><th>Course Name </th> </tr>");
				while (keys.hasNext()) {
					String key = keys.next();
					String ame = (String) str.get(key);
					out.print("<tr><td>" + key + "</td><td>" + ame + "</td></tr>");

				}
				out.println("</table>");
				out.println("<a type='button' href='Administrator.html'>back</a>");
				out.println("</body>");
				out.print("</html>");
				out.close();
			}

			// List of student
			String listStudent = request.getParameter("tableStudent");
			if (listStudent != null) {
				JSONObject str = p.studentList();
				Iterator<String> keys = str.keys();
				out.println("<html><head></head><body>");
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<th> Id </th><th> Name </th> </tr>");
				while (keys.hasNext()) {
					String key = keys.next();
					String ame = (String) str.get(key);
					out.print("<tr><td>" + key + "</td><td>" + ame + "</td></tr>");

				}
				out.println("</table>");
				out.println("<a type='button' href='Administrator.html'>back</a>");
				out.println("</body>");
				out.print("</html>");
				out.close();

			}
		}
	}
}
