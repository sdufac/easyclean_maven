package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import model.Cleaner;
import model.HTMLfunction;
import java.io.PrintWriter;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "AccueilCleaner", urlPatterns = { "/cleaner" })

public class AccueilCleaner extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilCleaner() {
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
		HttpSession session = request.getSession();
		response.getWriter().append("<h3>Accueil Cleaner</h3>");
		response.getWriter().append("<div style='display: flex'>");
		try (PrintWriter out = response.getWriter()) {
			response.setContentType("text/html;charset=UTF-8");

			HTMLfunction.profilUser(out, (Cleaner) session.getAttribute("user"));

			HTMLfunction.searchMission(out);
			out.append("");
		}
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
