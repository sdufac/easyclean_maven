package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

import model.HTMLfunction;
import model.Proprietaire;

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
		String[] tabMissions = HTMLfunction.proprioTabMission(user.getMissions(),user.getPostulation(),user);

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		HTMLfunction.head(response.getWriter());
		response.getWriter().append(
			"<body>"
			+"<h2>Accueil Proprietaire</h2>"
			+ user.getSecondName() + " " + user.getFirstName()+"<br>"
			+"<img src=\"./image/profile_picture/"+user.getUsername()+".png\" width=\"100\" height=\"100\">"
			+"<hr>"
			+"<button type=\"button\" onclick=\"location.href='addproperty'\">Enregistrer une propriétée</button>"
			+"<button type=\"button\" onclick=\"location.href='profil'\">Voir Profil</button>"
			+"<hr>"
			+"<div id=\"mission\">"
			+"	<h3>Vos missions en attente</h3>"
			+	tabMissions[0]
			+"	<hr>"
			+"	<h3>Vos missions en cours</h3>"
			+ 	tabMissions[1]
			+"	<hr>"
			+"	<h3>Votre historique de missions</h3>"
			+ 	tabMissions[2]
			+"</div>"
			+"<div id=\"form\">"
			+"	<hr>"
			+"	<h3>Poster une mission</h3>");
			HTMLfunction.formMission(user, response.getWriter());
			response.getWriter().append(
			"</div>"
			+"</body>"
			+"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
