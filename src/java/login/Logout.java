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


/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		out.print(
				"<html><head><script>setTimeout(() => {const e = document.getElementById('enroll'); e.style.display = 'none'; },10000);</script></head><body><h3 id=\"enroll\">You Have Logout SuccessFully...Thankyou!</h3></body></html>");
		RequestDispatcher rd = request.getRequestDispatcher("/index.html");
		rd.include(request, response);
		session.invalidate();
	}

}