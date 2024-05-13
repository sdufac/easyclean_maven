package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HTMLfunction;

import java.io.IOException;

/**
 * Servlet implementation class CreationCompte
 */
@WebServlet(name = "CreateAccount" , urlPatterns = {"/createaccount"})
public class CreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HTMLfunction.head(response.getWriter());

		response.getWriter().append("<body>"
		+ "<h2>Créer un compte</h2><hr>"
		+ 	"<form method=\"post\" action=\"controllercreation\" enctype=\"multipart/form-data\">"
		+		"Nom :<input type=\"text\" size=\"30\" name=\"nom\"><br>"
		+		"Prénom :<input type=\"text\" size=\"30\" name=\"prenom\"><br>"
		+		"Pseudonyme :<input type=\"text\" size=\"20\" name=\"pseudo\"><br>"
		+		"<label for=\"avatar\">Importer une photo de profil</label><br>"
		+		"<input type=\"file\" id=\"avatar\" name=\"avatar\" accept=\"image/png, image/jpeg\"/><br>"
		+		"Mot de passe :<input type=\"password\" size=\"30\" name=\"password\"><br>"
		+		"Adresse mail :<input type=\"text\" size=\"30\" name=\"mail\"><br>"
		+		"Age :<input type=\"text\" size=\"3\" name=\"age\"><br>"
		+		"Description :<br><textarea rows=\"4\" cols=\"30\" name=\"bio\">"
		+		"</textarea><br>"
		+		"Numero de téléphone :<input type=\"text\" size=\"20\" name=\"phonenumber\"><br>"
		+		"Date de naissance :<input type=\"date\" name=\"dateofbirth\"/><br>"
		+		"<fieldset>"
		+			"<legend>Choisissez votre rôle</legend>"
		+			"<div>"
		+				"<input type=\"radio\" id=\"rolec\" name=\"role\" value=\"cleaner\" checked/>"
		+				"<label for=\"rolec\">Cleaner</label>"
		+			"</div>"
		+			"<div>"
		+				"<input type=\"radio\" id=\"rolep\" name=\"role\" value=\"proprietaire\"/>"
		+				"<label for=\"rolep\">Propriétaire</label>"
		+			"</div>"
		+		"</fieldset><br>"
		+		"Taux horaire :<input type=\"text\" size=\"4\" name=\"salary\"><br>"	//Uniquement cleaner
		+		"<input type=\"submit\" value=\"Créer\">"
		+	"</form>"
		+ "</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
