package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import model.DAOacces;
import model.Mission;
import model.Property;
import model.Proprietaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "ControllerProprietaire", urlPatterns = { "/controllerproprietaire" })
public class ControllerProprietaire extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerProprietaire() {
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
        boolean errorFlag = false;
        String errorString = "ERREUR :";

        HttpSession session = request.getSession();
		Proprietaire user = (Proprietaire)session.getAttribute("user");
       
        //Durée de la mission
        double duration = 0;
        for(Property p : user.getProperties()){
            if(p.getIdProperty() == Integer.valueOf(request.getParameter("properties"))){
                duration = (p.getSurface()/50) * 0.5;
            }
        }

        //Id de la propriétée sélèctionné
        int idProperty = Integer.valueOf(request.getParameter("properties")); 
        
        //Instruction
        String instruction = "";
        if(request.getParameter("instruction").equals("")){
            errorFlag = true;
            errorString = errorString + " Instruction non valide";
        }
        else{
            instruction = request.getParameter("instruction");
        }
        
        //Date de la mission
        String dateMission = "";
        if(request.getParameter("mission_date").equals("")){
            errorFlag=true;
            errorString = errorString + " Date non valide";
        }
        else{
            dateMission = request.getParameter("mission_date");
        }
        
        //Id du proprietaire connecté
        int idProprietaire = user.getId();

        //Plage horraire de la mission
        int plageStart = 0;
        int plageEnd = 0;
        if(request.getParameter("plageStart").equals("") || request.getParameter("plageEnd").equals("")){
            errorFlag = true;
            errorString = errorString + "Plage horraire null"; 
        }else{
            plageStart = Integer.valueOf(request.getParameter("plageStart"));
            plageEnd = Integer.valueOf(request.getParameter("plageEnd")); 
        }

        if(plageEnd<plageStart || (plageEnd-plageStart)<duration || plageStart<8 || plageStart>20){
            errorFlag = true;
            errorString = errorString + "Plage horraire non valide";
        }

        //Envoie des infos en bdd si il n'y a pas d'erreur sinon redirection
        DAOacces bdd = new DAOacces("easy_clean", "root", "");
        if(!errorFlag){
            try{
                Statement stMission = bdd.getConnection().createStatement();
                String strQuery= "INSERT INTO mission (date_mission,time_mission,instruction,id_proprietaire,statut,id_propriete,proprietaire_start,proprietaire_end) VALUES ('"+dateMission+"',"+duration+",'"+instruction+"',"+idProprietaire+",1,"+idProperty+","+plageStart+","+plageEnd+");";
                stMission.executeUpdate(strQuery);

                String strGetNewMission="SELECT * FROM mission JOIN propriete ON id_propriete=propriete_id JOIN statut_mission ON statut=statut_id WHERE id_proprietaire ="+idProprietaire+" AND date_mission ='"+dateMission+"' AND time_mission="+duration+" AND instruction='"+instruction+"';";
                ResultSet rsNewMission = stMission.executeQuery(strGetNewMission);
                while (rsNewMission.next()) {
                    String adress = rsNewMission.getString(16)+" "+rsNewMission.getString(19)+" "+rsNewMission.getString(20);
                    Mission m = new Mission(rsNewMission.getString(2),rsNewMission.getDouble(3),rsNewMission.getString(4),rsNewMission.getDouble(10),rsNewMission.getDouble(11),adress,rsNewMission.getInt(7),user.getId(),rsNewMission.getString("nom"));
                    m.setIdMission(rsNewMission.getInt(1));
                    user.setMission(m);
                }
            }
            catch(SQLException e){
                System.err.println("Erreur");  e.printStackTrace();
            }
            bdd.disconnect();
            getServletContext().getRequestDispatcher("/proprietaire").forward(request,response);
        }
        else{
            bdd.disconnect();
            response.getWriter().append(errorString);
        }   
	}
}
