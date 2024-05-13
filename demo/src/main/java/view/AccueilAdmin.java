package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Cleaner;
import model.Litige;
import model.Proprietaire;
import model.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class AccueilAdmin
 */
@WebServlet(name = "AccueilAdmin", urlPatterns = { "/admin" })
public class AccueilAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilAdmin() {
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
		Admin admin = (Admin)session.getAttribute("user");
		ArrayList<Utilisateur> recherche = new ArrayList<Utilisateur>();
		if(session.getAttribute("recherche") !=null)recherche = (ArrayList<Utilisateur>)session.getAttribute("recherche");

		response.getWriter().append("<h2>Accueil Admin</h2>"
		+ "<hr>"
		+ "<h3>Rechercher un utilisateur :</h3>"
		+ "<form method=\"post\" action=\"controllerrechercheuser\">"
		+ "<input type=\"text\" name=\"name\"/>"
		+ "<input type=\"submit\" value=\"Rechercher\"/></form>");
		if(!recherche.isEmpty()){
			response.getWriter().append("<table>");
			for(Utilisateur s : recherche){
				response.getWriter().append("<tr>");
				if(s instanceof Cleaner){
					Cleaner cleaner = (Cleaner)s;
					response.getWriter().append("<th><img src=\"./image/profile_picture/"+cleaner.getUsername()+".png\" width=\"100\" height=\"100\"><th>"+cleaner.getSecondName()+"<th>"+cleaner.getFirstName()+"<th>Cleaner"
					+ "<th><form method=\"post\" action=\"controllerban\"><input type=\"hidden\" value=\"cleaner\" name=\"role\"/><input type=\"hidden\" value=\""+cleaner.getId()+"\" name=\"id\"/><input type=\"submit\" value=\"bannir\"/></form>");
				}
				if(s instanceof Proprietaire){
					System.out.println("Proprio // "+s.getId());
					Proprietaire proprio = (Proprietaire)s;
					response.getWriter().append("<th><img src=\"./image/profile_picture/"+proprio.getUsername()+".png\" width=\"100\" height=\"100\"><th>"+proprio.getSecondName()+"<th>"+proprio.getFirstName()+"<th>Proprietaire"
					+ "<th><form method=\"post\" action=\"controllerban\"><input type=\"hidden\" name=\"role\" value=\"proprietaire\"/><input type=\"hidden\" value=\""+proprio.getId()+"\" name=\"id\"/><input type=\"submit\" value=\"bannir\"/></form>");
				}
				response.getWriter().append("</tr>");
			}
			response.getWriter().append("</table>");
		}
		response.getWriter().append("<hr>"
		
		+" <h3>Litiges en cours<h3>"
		+ "<table>");

		for(Litige l:admin.getLitiges()){
			if(l.getStatutLitige().equals("enCours")){
				if(l.getAuthorc()!=null){
					response.getWriter().append("<tr><th>" + l.getAuthorc().getFirstName() + " " +l.getAuthorc().getSecondName()
					+"<th>"+l.getTextLitige() + "</tr><tr>"
					+"<th><img src=\"./image/litige/"+l.getImage1()+"\" width=\"200\"/>"
					+"<th><img src=\"./image/litige/"+l.getImage2()+"\" width=\"200\"/>"
					+"<th><img src=\"./image/litige/"+l.getImage3()+"\" width=\"200\"/></tr>"
					+"<tr><th><form action=\"controlleracceptlitige\" method=\"post\"><input type=\"hidden\" value=\""+l.getId()+"\" name=\"litigeid\"/>"
					+"<input type=\"submit\" value=\"accept\"/></form>"
					+"<form action=\"controllerrefuselitige\" method=\"post\"><input type=\"hidden\" value=\""+l.getId()+"\" name=\"litigeid\"/>"
					+"<input type=\"submit\" value=\"refuse\"/></form></tr>");
				}
				else if(l.getAuthorp()!= null){
					response.getWriter().append("<tr><th>" + l.getAuthorp().getFirstName() + " " +l.getAuthorp().getSecondName()
					+"<th>"+l.getTextLitige() + "</tr><tr>"
					+"<th><img src=\"./image/litige/"+l.getImage1()+"\" width=\"200\"/>"
					+"<th><img src=\"./image/litige/"+l.getImage2()+"\" width=\"200\"/>"
					+"<th><img src=\"./image/litige/"+l.getImage3()+"\" width=\"200\"/></tr>"
					+"<tr><th><form action=\"controlleracceptlitige\" method=\"post\"><input type=\"hidden\" value=\""+l.getId()+"\" name=\"litigeid\"/>"
					+"<input type=\"submit\" value=\"accept\"/></form>"
					+"<form action=\"controllerrefuselitige\" method=\"post\"><input type=\"hidden\" value=\""+l.getId()+"\" name=\"litigeid\"/>"
					+"<input type=\"submit\" value=\"refuse\"/></form></tr>");
				}
			}
			
		}
		response.getWriter().append("</table>");
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
