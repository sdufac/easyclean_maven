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
		response.getWriter().append("<html>"
				+ "<head>"
				+ "<title>Connexion</title>"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>"
				+ "</head>"
				+ "<body>"
				+ "<div class='container mt-3'>"
				+ "<h2>Connexion</h2>"
				+ "<hr>"
				+ "<form action='controllerconnection' method='POST' enctype='application/x-www-form-urlencoded'>"
				+ "<div class='form-group'>"
				+ "<label for='login'>Login</label>"
				+ "<input type='text' class='form-control' id='login' name='login' required>"
				+ "</div>"
				+ "<div class='form-group'>"
				+ "<label for='password'>Mot de passe</label>"
				+ "<input type='password' class='form-control' id='password' name='password' required>"
				+ "</div>"
				+ "<button type='submit' class='btn btn-primary'>Connexion</button>"
				+ "</form>"
				+ "</div>"
				+ "</body>"
				+ "</html>");

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
