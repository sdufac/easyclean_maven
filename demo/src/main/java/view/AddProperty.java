package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "addproperty", urlPatterns = { "/addproperty" })
public class AddProperty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProperty() {
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
		PrintWriter out = response.getWriter();
		out.append("<h2>Enregistrer une nouvelle propriétée</h2>"
		+ "<hr>"
		+ "<form action=\"controlleraddproperty\" method=\"POST\">"
		+ "<br>Adresse: <input type=\"text\" name=\"adress\" size=\"50\"/>"
		+ "<br>Ville: <input type=\"text\" name=\"ville\" size=\"50\"/>"
		+ "<br>Code postal: <input type=\"text\" name=\"codePostal\" size=\"5\"/>"
		+ "<br>Digicode (facultatif): <input type=\"text\" name=\"digicode\" size=\"10\"/>"
		+ "<br>Surface: <input type=\"text\" name=\"surface\" size=\"10\"/>m²"
		+ "<br><input type=\"submit\" value=\"Enregistrer\">"
		+ "</form>");
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
