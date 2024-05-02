
package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cleaner;
import model.DAOacces;
import model.Proprietaire;
import model.Utilisateur;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "controllercommentaire", urlPatterns = { "/controllercommentaire" })
public class ControllerCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerCommentaire() {
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
        String strQuery = "";
        
        if(session.getAttribute("user") instanceof Proprietaire){
            Proprietaire user = (Proprietaire)session.getAttribute("user");
            Cleaner user2 = (Cleaner)session.getAttribute("profil");

            strQuery ="INSERT INTO commentaire (idAuthor,idUser,idMission,commentaire,note) VALUES ("+user.getId()+","+user2.getId()+",33,\""+request.getParameter("commentaire")+"\","+Float.valueOf(request.getParameter("note"))+")";
            DAOacces bdd = new DAOacces("easy_clean", "root", "");
            try{
                bdd.getConnection().createStatement().executeUpdate(strQuery);
                user2.setComment(Float.valueOf(request.getParameter("note")), request.getParameter("comment"));
                getServletContext().getRequestDispatcher("/proprietaire").forward(request,response);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{
            Cleaner user = (Cleaner)session.getAttribute("user");
            Proprietaire user2 = (Proprietaire)session.getAttribute("profil");

            strQuery = "INSERT INTO commentaire (idAuthor,idUser,commentaire,note) VALUES ("+user.getId()+","+user2.getId()+",\""+request.getParameter("commentaire")+"\","+Float.valueOf(request.getParameter("note"))+")";
            DAOacces bdd = new DAOacces("easy_clean", "root", "");
            try{
                bdd.getConnection().createStatement().executeUpdate(strQuery);
                user2.setComment(Float.valueOf(request.getParameter("note")), request.getParameter("comment"));
                getServletContext().getRequestDispatcher("/cleaner").forward(request,response);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
		
	}

}
