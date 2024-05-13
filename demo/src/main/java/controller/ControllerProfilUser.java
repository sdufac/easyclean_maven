package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cleaner;
import model.DAOacces;
import model.Mission;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "controllerprofiluser", urlPatterns = { "/controllerprofiluser" })
public class ControllerProfilUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerProfilUser() {
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
		DAOacces bdd = new DAOacces("easy_clean", "toto", "titi");
		String strGetUser = "SELECT * FROM users WHERE id_user =" + request.getParameter("id");

		try {
			Statement stGetUser = bdd.getConnection().createStatement();
			ResultSet rsGetUser = stGetUser.executeQuery(strGetUser);

			while (rsGetUser.next()) {
				if (rsGetUser.getString("role").equals("Cleaner")) {
					// Creation de l'utilisateur
					Cleaner user = new Cleaner(rsGetUser.getString("first_name"), rsGetUser.getString("second_name"),
							rsGetUser.getString("username"), rsGetUser.getString("mail"),
							rsGetUser.getString("password"), rsGetUser.getInt("age"), rsGetUser.getString("bio"),
							rsGetUser.getInt("phone_number"), rsGetUser.getString("date_of_birth"),
							rsGetUser.getFloat("note"), rsGetUser.getInt("nb_mission"), rsGetUser.getInt("perimeter"),
							rsGetUser.getInt("tarif_horaire"));
					user.setDateOfCreation(rsGetUser.getDate("date_creation"));
					user.setId(rsGetUser.getInt(1));

					// Creation des commentaires
					Statement comment = bdd.getConnection().createStatement();
					String strComment = "SELECT * FROM commentaire WHERE idUser =" + user.getId();
					ResultSet rsComment = comment.executeQuery(strComment);

					while (rsComment.next()) {
						user.setComment(rsComment.getFloat("note"), rsComment.getString("commentaire"));
					}
					user.getMoy();

					HttpSession session = request.getSession();
					session.setAttribute("profil", user);
					getServletContext().getRequestDispatcher("/profiluser").forward(request, response);
				} else {
					response.getWriter().append("ERREUR");
				}
			}
			response.getWriter().append("ERREUR" + request.getParameter("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
