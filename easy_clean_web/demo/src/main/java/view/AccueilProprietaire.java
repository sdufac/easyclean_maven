package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HTMLfunction;
import model.Proprietaire;

import java.io.IOException;

/**
 * Servlet implementation class AccueilProprietaire
 */
@WebServlet(name = "AccueilProprietaire" , urlPatterns = {"/proprietaire"})
public class AccueilProprietaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilProprietaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Proprietaire user = (Proprietaire)session.getAttribute("user");
		String[] tabMissions = HTMLfunction.proprioTabMission(user.getMissions());

		response.getWriter().append("<h2>Accueil Proprietaire</h2><hr>"
		+"<div id=\"mission\"><h3>Vos missions en attente</h3>"
		+ tabMissions[0]
		+"<hr>"
		+"<h3>Vos missions en cours</h3>"
		+ tabMissions[1]
		+"<hr>"
		+"<h3>Votre historique de missions</h3>"
		+ tabMissions[2]
		+"</div>"
		+"<div id=\"form\">"
		+"<h3>Poster une mission</h3>"
		+"<form action=\"controllerproprietaire\" method=\"POST\" enctype=\"application/x-www-form-urlencoded\">"
		+"Propriétée<br>"
		+"Instruction<br>"
		+"<input type=\"text\" id=\"instruction\" size=\"100\"/><br>"
		+"Date de la mission<br>"
		+"Durée de la mission"
		+"</div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
