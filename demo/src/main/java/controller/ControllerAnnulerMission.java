package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOacces;
import model.Mission;
import model.Proprietaire;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "ControllerAnnulerMission", urlPatterns = { "/annulermission" })
public class ControllerAnnulerMission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAnnulerMission() {
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
		int id = Integer.valueOf(request.getParameter("idmission")); 

        DAOacces bdd = new DAOacces("easy_clean", "root", "");
        try{
            Statement stDelete = bdd.getConnection().createStatement();
            String strQuery = "DELETE FROM mission WHERE mission_id ="+id+";";
            stDelete.executeUpdate(strQuery);

            HttpSession session = request.getSession();
            Proprietaire user =(Proprietaire) session.getAttribute("user");

            int i = 0;
            int index = 0;
            for(Mission m: user.getMissions()){
                if(m.getIdMission() == id){
                    index = i;
                }
                i++;
            }

            user.getMissions().remove(index);

            getServletContext().getRequestDispatcher("/proprietaire").forward(request,response);
        }catch(SQLException e){
            System.err.println("Erreur");  e.printStackTrace();
        }
	}

}
