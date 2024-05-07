package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HTMLfunction;

import java.io.IOException;

/**
 * Servlet implementation class Connection
 */
@WebServlet(name = "Connection", urlPatterns = { "/connection" })
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connection() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		HTMLfunction.head(response.getWriter());
		response.getWriter().append("<body><h2>Connection</h2><hr>"
				+ "<form action=\"controllerconnection\" method=\"POST\" enctype=\"application/x-www-form-urlencoded\">"
				+ "Login <input type=\"text\" name=\"login\">"
				+ "Mot de passe <input type=\"password\" name=\"password\">"
				+ "<br><input type=\"submit\" value=\"Connection\">"
				+ "</form></body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
