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

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userid") == null) {
			out.print("<body><h3 id='delete'>Please Login First!</h3><script src='validate.js'></script></body>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.include(request, response);
		} else {
			Administrator admin = new Administrator();
			StudentProcess student = new StudentProcess();
			// mycourse
			String myCourse = request.getParameter("mycourseSubmit");
			if (myCourse != null) {
				JSONObject str = student.studentCourse((String) session.getAttribute("userid"));
				if (str.isEmpty()) {
					out.print("<html><body>");
					out.print("No Course Enrolled");
					out.println("<a type='button' href='Student.html'>Back</a>");
					out.print("</body></html>");
				} else {
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
					out.println("<a type='button' href='Student.html'>Back</a>");
					out.println("</body>");
					out.print("</html>");
					out.close();
				}
			}

			// Course overview
			String courseOverView = request.getParameter("courseOverview");
			if (courseOverView != null) {
				JSONObject str = student.courseList();
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
				out.println("<a type='button' href='Student.html'>Back</a>");
				out.println("</body>");
				out.print("</html>");
				out.close();
			}

			// coursedetails
			String courseid = request.getParameter("enrollcourseId");
			String coursedetailsId = request.getParameter("coursedetailsSubmit");
			if (coursedetailsId != null) {
				JSONObject str = student.coursedetails(courseid);
				if (str.isEmpty()) {
					out.print("<html><body>");
					out.print("No Data Found");
					out.println("<a type='button' href='Student.html'>Back</a>");
					out.print("</body></html>");
				} else {
					Iterator<String> keys = str.keys();
					out.println("<html><head></head><body>");
					out.println("<table border='1'>");
					out.println("<tr>");
					out.println("<th>Student Id </th><th>Student Name </th> </tr>");
					while (keys.hasNext()) {
						String key = keys.next();
						String ame = (String) str.get(key);
						out.print("<tr><td>" + key + "</td><td>" + ame + "</td></tr>");

					}
					out.println("</table>");
					out.println("<a type='button' href='Student.html'>Back</a>");
					out.println("</body>");
					out.print("</html>");
					out.close();
				}
			}

			// Enroll Course
			String enrollcourseId = request.getParameter("enrollcourseId");
			String enrollSubmit = request.getParameter("enrollcourseIdSubmit");
			if (enrollSubmit != null) {
				try {
					if (student.enRollCourse((String) session.getAttribute("userid"), enrollcourseId) == 1) {
						out.print("<body><h3 id='enroll'>Course Enrolled Successfully!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Student.html");
						rd.include(request, response);
					} else if (student.enRollCourse((String) session.getAttribute("userid"), enrollcourseId) == 2) {
						out.print("<body><h3 id='delete'>You have already enrolled this course!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Student.html");
						rd.include(request, response);
					} else if (student.enRollCourse((String) session.getAttribute("userid"), enrollcourseId) == 3) {
						out.print("<body><h3 id='delete'>No Course Found!</h3><script src='validate.js'></script></body>");
						RequestDispatcher rd = request.getRequestDispatcher("/Student.html");
						rd.include(request, response);
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
	}
}
