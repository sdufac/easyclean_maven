package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cleaner;
import model.Proprietaire;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "profiluser", urlPatterns = { "/profiluser" })
public class ProfilUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilUser() {
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

        if(session.getAttribute("profil") instanceof Cleaner){
            Cleaner cleaner = (Cleaner)session.getAttribute("profil");
            String html = "";

             html = "<h2>Profil Cleaner</h2><hr>"
            +"Nom: " + cleaner.getFirstName() +"<br>"
            +"Prenom: " + cleaner.getSecondName() +"<br>"
            +"Age: " + cleaner.getAge() +"<br>"
            +"Pseudonyme: " + cleaner.getUsername() +"<br>"
            +"Email: " +cleaner.getEmail() +"<br>"
            +"Description: " +cleaner.getDescription() +"<br>"
            +"Note global: "+cleaner.getGlobalGrade()
            +"<hr><h2>Commentaire</h2>";
            if(cleaner.getComment().size()>0){
                html = html+"<table>";
                for(Map.Entry<Float, String> comment :cleaner.getComment().entrySet()){
                    html = html + "<tr><th>"+comment.getKey()+"</th><th>"+comment.getValue()+"</th></tr>";
                }
                html = html + "</table>";
            }else{
                html = html + "Pas encore de commentaire posté sur ce profil";
            }
            html = html 
            + "<hr><h2>Laisser un commentaire</h2>"
            + "<form method=\"post\" action=\"controllercommentaire\">"
            +"Note<br>"
            + "<input type=\"text\" name=\"note\" size=\"3\"/><br>"
            + "Commentaire<br>"
            + "<input type=\"text\" name=\"commentaire\" size=\"50\"/>"
            + "<br><input type =\"submit\" value=\"Poster un commentaire\"/></form>"
            + "<br><button type=\"button\" onclick=\"location.href='proprietaire'\">Retour</button>"; 
            PrintWriter out = response.getWriter();
            out.append(html);
        }
        else if(session.getAttribute("profil") instanceof Proprietaire){

        }
        else{
            response.getWriter().append("ERROR");
        }
	}

}
