package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "litige", urlPatterns = { "/litige" })
public class VueLitige extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VueLitige() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String html = "<h2>Déclarer un litige</h2><hr>"
		+ "<form method=\"post\" action=\"controllerlitige\" enctype=\"multipart/form-data\"/>"
		+ "Expliquez le problème <br>"
		+ "<input type=\"text\" name=\"text\" rows=\"10\" cols=\"50\"/><br>"
		+ "Déposez les preuves photos<br>"
		+ "<input type=\"file\" id=\"image1\" name=\"image1\" accept=\"image/png,image/png\"/><br>"
		+ "<input type=\"file\" id=\"image2\" name=\"image2\" accept=\"image/png,image/png\"/><br>"
		+ "<input type=\"file\" id=\"image3\" name=\"image3\" accept=\"image/png,image/png\"/><br>"
		+ "<input type=\"hidden\" name=\"idmission\" value=\""+request.getParameter("idmission")+"\"/>"
		+ "<br><br><input type=\"submit\" value=\"Valider\"/></form>";

		response.getWriter().append(html);
	}
}
