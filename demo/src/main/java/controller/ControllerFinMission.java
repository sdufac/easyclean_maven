package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOacces;
import model.Proprietaire;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "FinMission", urlPatterns = { "/finmission" })
public class ControllerFinMission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerFinMission() {
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
		Proprietaire user = (Proprietaire) session.getAttribute("user");
		int idMission = Integer.valueOf(request.getParameter("idmission"));

		DAOacces bdd = new DAOacces("easy_clean", "toto", "toto");
		String strUpdateMission = "UPDATE mission SET statut =5 WHERE mission_id =" + idMission;
		try {
			bdd.getConnection().createStatement().executeUpdate(strUpdateMission);
			user.getMissionById(idMission).setStatut("finished");

			getServletContext().getRequestDispatcher("/proprietaire").forward(request, response);
		} catch (SQLException e) {
			System.out.println("ERREUR :");
			e.printStackTrace();
		}

	}

}
