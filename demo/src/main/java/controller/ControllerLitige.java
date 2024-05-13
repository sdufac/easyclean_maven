package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.DAOacces;
import model.Litige;
import model.Mission;
import model.Proprietaire;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "controllerlitige", urlPatterns = { "/controllerlitige" })
@MultipartConfig
public class ControllerLitige extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerLitige() {
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
        Proprietaire user = (Proprietaire)session.getAttribute("user");
        Mission m = user.getMissionById(Integer.valueOf(request.getParameter("idmission")));

        //Récuperation des fichiers
        InputStream file1 = request.getPart("image1").getInputStream();
        InputStream file2 = request.getPart("image2").getInputStream();
        InputStream file3 = request.getPart("image3").getInputStream();
		
        Files.copy(file1, new File(getServletContext().getRealPath(getServletInfo()) + "\\image\\litige\\"+user.getId()+"_"+m.getIdMission()+"_1.png").toPath(),StandardCopyOption.REPLACE_EXISTING);
        Files.copy(file2, new File(getServletContext().getRealPath(getServletInfo()) + "\\image\\litige\\"+user.getId()+"_"+m.getIdMission()+"_2.png").toPath(),StandardCopyOption.REPLACE_EXISTING);
        Files.copy(file3, new File(getServletContext().getRealPath(getServletInfo()) + "\\image\\litige\\"+user.getId()+"_"+m.getIdMission()+"_3.png").toPath(),StandardCopyOption.REPLACE_EXISTING);

        //Litige en bdd
        Litige l = new Litige(m.getCleaner(),request.getParameter("text"),user.getId()+"_"+m.getIdMission()+"_1.png",user.getId()+"_"+m.getIdMission()+"_2.png",user.getId()+"_"+m.getIdMission()+"_3.png",m,"enCours");
        user.setLitiges(l);

        DAOacces bdd = new DAOacces("easy_clean","root","");
        try{
            Statement stLitige = bdd.getConnection().createStatement();
            String strQuery = "INSERT INTO litige (text_litige,image1,image2,image3,id_autor,id_mission,id_statut) VALUES (\'"+l.getTextLitige()+"\',\'"+l.getImage1()+"\',\'"+l.getImage2()+"\',\'"+l.getImage3()+"\',"+user.getId()+","+m.getIdMission()+",1)";

            stLitige.executeUpdate(strQuery);
            getServletContext().getRequestDispatcher("/proprietaire").forward(request,response);
        }catch(SQLException e){
            e.printStackTrace();
        }
	}

}

