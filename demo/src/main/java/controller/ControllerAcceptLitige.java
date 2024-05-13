package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.DAOacces;
import model.Litige;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "ControllerAcceptLitige", urlPatterns = { "/controlleracceptlitige" })
public class ControllerAcceptLitige extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAcceptLitige() {
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
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("user");
		Litige litige = admin.getLitigeById(Integer.valueOf(request.getParameter("litigeid")));

		DAOacces bdd = new DAOacces("easy_clean", "toto", "titi");
		String stQuery = "UPDATE `litige` SET `id_statut` = '3' WHERE `litige`.`litige_id` =" + litige.getId();
		try {
			Statement stLitige = bdd.getConnection().createStatement();
			stLitige.executeUpdate(stQuery);
			litige.setStatutLitige("accepte");
			admin.getLitiges().remove(litige);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher("/admin").forward(request, response);
	}

}
