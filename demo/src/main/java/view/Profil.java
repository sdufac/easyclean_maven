package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Proprietaire;
import model.Cleaner;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "Profil", urlPatterns = { "/profil" })
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profil() {
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
        String html = "";

        if(session.getAttribute("user") instanceof Proprietaire){
            Proprietaire user = (Proprietaire)session.getAttribute("user");
            html = "<h2>Profil proprietaire</h2><hr>"
            +"Nom: " + user.getFirstName() +"<br>"
            +"Prenom: " + user.getSecondName() +"<br>"
            +"Age: " + user.getAge() +"<br>"
            +"Pseudonyme: " + user.getUsername() +"<br>"
            +"Email: " +user.getEmail() +"<br>"
            +"Nombre de mission complétées: " +user.getMissions().size() + "<br>"
            +"Description: " +user.getDescription() +"<br>";

            PrintWriter out = response.getWriter();
            out.append(html);
        }
        else if(session.getAttribute("user") instanceof Cleaner){
            Cleaner user = (Cleaner) session.getAttribute("user");

            html = "<h2>Profil Cleaner</h2><hr>"
            +"Nom: " + user.getFirstName() +"<br>"
            +"Prenom: " + user.getSecondName() +"<br>"
            +"Age: " + user.getAge() +"<br>"
            +"Pseudonyme: " + user.getUsername() +"<br>"
            +"Email: " +user.getEmail() +"<br>"
            +"Description: " +user.getDescription() +"<br>";

            PrintWriter out = response.getWriter();
            out.append(html);
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
