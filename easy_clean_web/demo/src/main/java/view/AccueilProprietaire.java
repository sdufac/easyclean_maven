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

		response.getWriter().append("<h2>Accueil Proprietaire</h2><hr>"
		+"<h3>Vos missions</h3>"
		+ HTMLfunction.tabMission(user.getMissions()));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
