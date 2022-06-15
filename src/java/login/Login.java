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


/**
 * Servlet implementation class StudentLoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = new Administrator();
		StudentProcess student = new StudentProcess();
		PrintWriter out = response.getWriter();
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("Expires", "0");
		
		HttpSession session=null;
		//admin login
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String submit = request.getParameter("submit");
		response.setContentType("text/html");
		if (submit != null) {
			try {
				if (admin.adminDb(name, password)) {
					session = request.getSession();
					session.setAttribute("username", name);
					response.sendRedirect("Administrator.html");
					RequestDispatcher rd = request.getRequestDispatcher("/Administrator.html");
					rd.include(request, response);
					out.close();
				}

				else {
					out.print("<html>" + "<body>" + "<h3>" + "Sorry UserName or Password Error!" + "</h3>" + "</body>"
							+ "</html>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.html");
					rd.include(request, response);
					out.close();
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		
		//student register
		String studentId = request.getParameter("s_id");
		String studentName = request.getParameter("s_name");
		String studentSem = request.getParameter("s_sem");
		String register = request.getParameter("register");
		if (register != null) {
			try {
				if (admin.student(studentId, studentName, studentSem)) {
					out.print("<html><head><script>setTimeout(() => {const e = document.getElementById('enroll'); e.style.display = 'none'; },10000);</script></head>" + "<body>" + "<h3 id='enroll'>" + "You Have Successfully Registered" + "</h3>"
							+ "</body>" + "</html>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.html");
					rd.include(request, response);
				} else {
					out.print("<html><head><script>setTimeout(() => {const e = document.getElementById('delete'); e.style.display = 'none'; },10000);</script></head>" + "<body>" + "<h3 id='delete'>" + "You are already Registered..Please Login"
							+ "</h3>" + "</body>" + "</html>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.html");
					rd.include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//student login
		String loginStudentId = request.getParameter("l_s_id");
		String loginStudentName = request.getParameter("l_s_name");
		String login = request.getParameter("login");
		if (login != null) {
			try {
				if (student.userDb(loginStudentId, loginStudentName)) {
					session = request.getSession();
					session.setAttribute("userid", loginStudentId);
					out.print("<html>" + "<body>" + "<h3 style=color:black;>" + "Welcome " + loginStudentName + "</h3>"
							+ "</body>" + "</html>");
					RequestDispatcher rd = request.getRequestDispatcher("/Student.html");
					rd.include(request, response);
					
				} else {
					out.print("<html>" + "<body>" + "<h3>" + "Sorry Id or Name is Wrong!" + "</h3>" + "</body>"
							+ "</html>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.html");
					rd.include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
