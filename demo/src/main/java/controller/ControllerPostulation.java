package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOacces;
import model.Postulation;
import model.Proprietaire;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "ControllerPostulation", urlPatterns = { "/controllerpostulation" })
public class ControllerPostulation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerPostulation() {
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
		HttpSession session = request.getSession();
		Proprietaire user = (Proprietaire) session.getAttribute("user");
		Postulation p = user.getPostulationById(Integer.valueOf(request.getParameter("idpostulation")));

		System.out.println("ID:" + p.getCleaner().getId());
		// TODO Auto-generated method stub
		DAOacces bdd = new DAOacces("easy_clean", "toto", "titi");
		try {
			bdd.getConnection().createStatement()
					.executeUpdate("UPDATE mission SET id_cleaner =" + p.getCleaner().getId() + ",cleaner_start="
							+ p.getHoraireStart() + ",cleaner_end=" + p.getHoraireEnd() + ",final_salary="
							+ p.getSalaireCleaner() + ",statut =3 WHERE mission_id =" + p.getMission().getIdMission());
			user.getMissionById(p.getMission().getIdMission()).setStatut("waiting");
			user.getMissionById(p.getMission().getIdMission()).setCleaner(p.getCleaner());
			user.getMissionById(p.getMission().getIdMission()).setHoraireCleaner(p.getHoraireStart(),
					p.getHoraireEnd());
			user.removePostulation(p.getMission().getIdMission());

			getServletContext().getRequestDispatcher("/proprietaire").forward(request, response);
		} catch (SQLException e) {
			System.err.println("Erreur");
			e.printStackTrace();
		}
	}
}
