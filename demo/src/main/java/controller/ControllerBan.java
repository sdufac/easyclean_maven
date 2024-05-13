package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.DAOacces;
import model.Utilisateur;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "controllerban", urlPatterns = { "/controllerban" })
public class ControllerBan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerBan() {
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
        System.out.println(Integer.parseInt(request.getParameter("id")));
		String strBan = "DELETE FROM users WHERE id_user ="+Integer.parseInt(request.getParameter("id"));
        DAOacces bdd = new DAOacces("easy_clean", "root", "");
        try{
            Statement stBan = bdd.getConnection().createStatement();
            stBan.executeUpdate(strBan);
            HttpSession session = request.getSession();
            session.removeAttribute("recherche");
            System.out.println("RECHERCHE// "+session.getAttribute("recherche"));
            Admin admin = (Admin)session.getAttribute("user");

            if(request.getParameter("role").equals("cleaner")){
                admin.getCleaners().remove(admin.getCleanerById(Integer.parseInt(request.getParameter("id"))));
            }
            else if(request.getParameter("role").equals("proprietaire")){
                admin.getProprietaires().remove(admin.getProprietaireById(Integer.parseInt(request.getParameter("id"))));
            }
            getServletContext().getRequestDispatcher("/admin").forward(request,response);
            
        }catch(SQLException e){
            System.out.println("ERREUR"); e.printStackTrace();
        }
	}

}
