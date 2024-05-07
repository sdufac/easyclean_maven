package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import model.Cleaner;
import model.DAOacces;
import model.Litige;
import model.Mission;
import model.Postulation;
import model.Property;
import model.Proprietaire;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class ControllerConnection
 */
@WebServlet(name = "ControllerConnection" , urlPatterns = {"/controllerconnection"})
public class ControllerConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("login");
		String password = request.getParameter("password");
		
		boolean isFailed = true;
		DAOacces bdd = new DAOacces("easy_clean","root","");

		try {
			String strQuery= "SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"';";
			Statement stLogin = bdd.getConnection().createStatement();
			ResultSet rsLogin = stLogin.executeQuery(strQuery);
			
			while(rsLogin.next()) {
				isFailed = false;
				if(rsLogin.getString("role").equals("Cleaner")) {
					//Creation de l'utilisateur
					Cleaner user = new Cleaner(rsLogin.getString("first_name"),rsLogin.getString("second_name"),rsLogin.getString("username"),rsLogin.getString("mail"),rsLogin.getString("password"),rsLogin.getInt("age"),rsLogin.getString("bio"),rsLogin.getInt("phone_number"),rsLogin.getString("date_of_birth"),rsLogin.getFloat("note"),rsLogin.getInt("nb_mission"),rsLogin.getInt("perimeter"),rsLogin.getInt("tarif_horaire"));
					user.setDateOfCreation(rsLogin.getDate("date_creation"));
					user.setId(rsLogin.getInt(1));
					
					//Creation des postulations
					Statement postulation = bdd.getConnection().createStatement();
					String strPostulation ="SELECT * FROM postulation JOIN mission ON idMission = mission_id JOIN statut_mission ON statut=statut_id JOIN propriete ON id_propriete = propriete_id WHERE idCleaner ="+user.getId()+";";
					ResultSet rsPostulation = postulation.executeQuery(strPostulation);
					
					while(rsPostulation.next()) {
						String adress = rsPostulation.getString("adress")+" "+rsPostulation.getString("ville")+" "+rsPostulation.getString("code_postal");
						Mission m = new Mission(rsPostulation.getString("date_mission"),rsPostulation.getDouble("time_mission"),rsPostulation.getString("instruction"),rsPostulation.getDouble("proprietaire_start"),rsPostulation.getDouble("proprietaire_end"),adress,rsPostulation.getInt("id_proprietaire"),user.getId(),rsPostulation.getString("nom"));
						m.setIdMission(rsPostulation.getInt(4));
						
						user.addPostulation(m);
					}
					
					Statement missionCleaner = bdd.getConnection().createStatement();
					String strMissionCleaner ="SELECT * FROM mission JOIN statut_mission ON statut=statut_id JOIN propriete ON id_propriete = propriete_id WHERE id_cleaner ="+user.getId();
					ResultSet rsMissionCleaner = missionCleaner.executeQuery(strMissionCleaner);
					
					while(rsMissionCleaner.next()) {
						String adress = rsMissionCleaner.getString("adress")+" "+rsMissionCleaner.getString("ville")+" "+rsMissionCleaner.getString("code_postal");
						Mission m = new Mission(rsMissionCleaner.getString("date_mission"),rsMissionCleaner.getDouble("time_mission"),rsMissionCleaner.getString("instruction"),rsMissionCleaner.getDouble("proprietaire_start"),rsMissionCleaner.getDouble("proprietaire_end"),adress,rsMissionCleaner.getInt("id_proprietaire"),user.getId(),rsMissionCleaner.getString("nom"));
						m.setIdMission(rsMissionCleaner.getInt("mission_id"));
						user.addPostulation(m);
					}

					//Creation des commentaires
					Statement comment = bdd.getConnection().createStatement();
					String strComment ="SELECT * FROM commentaire WHERE idUser ="+user.getId();
					ResultSet rsComment = comment.executeQuery(strComment);
					
					while(rsComment.next()) {
						user.setComment(rsComment.getFloat("note"), rsComment.getString("commentaire"));
					}
					user.getMoy();
					
					//Mettre le cleaner en variable de session et rediriger
					
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					
					getServletContext().getRequestDispatcher("/cleaner").forward(request,response);
				}
				else if(rsLogin.getString("role").equals("Proprietaire")) {			
					
					

					//Creation du proprietaire
					Proprietaire user = new Proprietaire(rsLogin.getString("first_name"), rsLogin.getString("second_name"),rsLogin.getString("username"),rsLogin.getString("mail"), rsLogin.getString("password"),rsLogin.getInt("age"),  rsLogin.getString("bio"), rsLogin.getInt("phone_number"),rsLogin.getString("date_of_birth"),rsLogin.getFloat("note"));
					user.setDateOfCreation(rsLogin.getDate("date_creation"));
					user.setId(rsLogin.getInt(1));
					

					//Creation des propriete
					String strQuery2= "SELECT * FROM propriete WHERE proprietaire_id = "+user.getId()+";";
					ResultSet rsLogin2 = bdd.getStLogin().executeQuery(strQuery2);
					
					while(rsLogin2.next()) {
						
						String adress = rsLogin2.getString(2)+" "+rsLogin2.getString(5)+" "+rsLogin2.getInt(6);
						user.setProperties(new Property(rsLogin2.getInt(1),adress,rsLogin2.getInt(3),rsLogin2.getInt(7)));
					}
					
					System.out.println("//AFTER CREATING PROPERTY");
					//Creation des missions
					Statement proprietee = bdd.getConnection().createStatement();
					strQuery ="SELECT * FROM mission JOIN propriete ON id_propriete=propriete_id JOIN statut_mission ON statut=statut_id LEFT JOIN users ON id_cleaner=id_user WHERE id_proprietaire =" +user.getId();
					ResultSet rsMission = proprietee.executeQuery(strQuery);
					
					while(rsMission.next()) {
						String adress = rsMission.getString("adress")+" "+rsMission.getString("ville")+" "+rsMission.getString("code_postal");
						Mission m = new Mission(rsMission.getString("date_mission"),rsMission.getDouble("time_mission"),rsMission.getString("instruction"),rsMission.getDouble("proprietaire_start"),rsMission.getDouble("proprietaire_end"),adress,rsMission.getInt("id_propriete"),user.getId(),rsMission.getString("nom"));
						m.setIdMission(rsMission.getInt("mission_id"));
						if(m.getStatut().equals("waiting") || m.getStatut().equals("finished") || m.getStatut().equals("cleanerFinished")){
							Cleaner cleaner = new Cleaner(rsMission.getString("first_name"),rsMission.getString("second_name"),rsMission.getString("username"),rsMission.getString("mail"),rsMission.getString("password"),rsMission.getInt("age"),rsMission.getString("bio"),rsMission.getInt("phone_number"),rsMission.getString("date_of_birth"),rsMission.getFloat("note"),rsMission.getInt("nb_mission"),rsMission.getInt("perimeter"),rsMission.getInt("tarif_horaire"));
							cleaner.setId(rsMission.getInt("id_user"));	
							m.setHoraireCleaner(rsMission.getDouble("cleaner_start"),rsMission.getDouble("cleaner_end"));
							m.setCleaner(cleaner);
							user.setMission(m);
						}
						else if(m.getStatut().equals("available")){
							user.setMission(m);
						}
					}

					System.out.println("//AFTER CREATING MISSION");

					//Creation des postulations
					if(user.getMissions().size()>0){
						
						String strPostulation = "SELECT * FROM postulation JOIN users ON idCleaner=id_user WHERE idMission =";
						int count = 1;

						for(Mission m: user.getMissions()){
							if(count<user.getMissions().size() && user.getMissions().size()>0){
								strPostulation = strPostulation + m.getIdMission()+" OR idMission =";
							}
							else if(count == user.getMissions().size() && user.getMissions().size()>0){
								strPostulation = strPostulation + m.getIdMission();
							}
							count++;
						}
						
						ResultSet rsPostulation = proprietee.executeQuery(strPostulation);

						while (rsPostulation.next()) {
							Mission pMission = null;
							for(Mission m: user.getMissions()){
								if(m.getIdMission() == rsPostulation.getInt("idMission")){
									pMission = m;
								}
							}
							Cleaner cleaner = new Cleaner(rsPostulation.getString("first_name"),rsPostulation.getString("second_name"),rsPostulation.getString("username"),rsPostulation.getString("mail"),rsPostulation.getString("password"),rsPostulation.getInt("age"),rsPostulation.getString("bio"),rsPostulation.getInt("phone_number"),rsPostulation.getString("date_of_birth"),rsPostulation.getFloat("note"),rsPostulation.getInt("nb_mission"),rsPostulation.getInt("perimeter"),rsPostulation.getInt("tarif_horaire"));
							cleaner.setId(rsPostulation.getInt("id_user"));
							Postulation p = new Postulation(rsPostulation.getInt("idPostulation"), pMission, cleaner, rsPostulation.getFloat("horaireStart"), rsPostulation.getFloat("horaireEnd"), rsPostulation.getFloat("salaireCleaner"));
							user.setPostulation(p);
						}
					}
					System.out.println("//AFTER CREATING POSTULATION");

					//Creation des commentaires
					Statement comment = bdd.getConnection().createStatement();
					String strComment ="SELECT * FROM commentaire WHERE idUser ="+user.getId();
					ResultSet rsComment = comment.executeQuery(strComment);
					
					while(rsComment.next()) {
						user.setComment(rsComment.getFloat("note"), rsComment.getString("commentaire"));
					}
					user.getMoy();

					System.out.println("//AFTER CREATING COMMENT");

					//Set litiges
					Statement stLitige = bdd.getConnection().createStatement();
					String strLitige = "SELECT * FROM litige WHERE id_autor ="+user.getId();
					ResultSet rsLitige = stLitige.executeQuery(strLitige);
					
					while(rsLitige.next()){
						Mission missionl = user.getMissionById(rsLitige.getInt("id_mission"));
						Cleaner litigec = missionl.getCleaner();
						Litige l = new Litige(litigec,rsLitige.getString("text_litige"),rsLitige.getString("image1"),rsLitige.getString("image2"),rsLitige.getString("image3"),missionl);
						user.setLitiges(l);
					}

					System.out.println("//AFTER CREATING LITIGE");

					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					
					getServletContext().getRequestDispatcher("/proprietaire").forward(request,response);
				}
			else if(rsLogin.getString("role").equals("Admin")) {
				Admin admin = new Admin(rsLogin.getString("username"), rsLogin.getString("first_name"),rsLogin.getString("second_name"),rsLogin.getInt("age"), rsLogin.getString("password"),rsLogin.getString("mail"),  rsLogin.getString("bio"), rsLogin.getInt("phone_number"),rsLogin.getString("date_of_birth"),rsLogin.getFloat("note"));	
				
				String strMissionAdmin= "SELECT * FROM mission JOIN propriete ON id_propriete=propriete_id JOIN statut_mission ON statut = statut_id;";
				Statement stLoginAdmin = bdd.getConnection().createStatement();
				ResultSet rsLoginMissionsAdmin = stLoginAdmin.executeQuery(strMissionAdmin);
						
				while(rsLoginMissionsAdmin.next()) {
					String adress = rsLoginMissionsAdmin.getString("adress")+" "+rsLoginMissionsAdmin.getString("ville")+" "+rsLoginMissionsAdmin.getString("code_postal");
					
					Mission m = new Mission(rsLoginMissionsAdmin.getString("date_mission"),rsLoginMissionsAdmin.getDouble("time_mission"),rsLoginMissionsAdmin.getString("instruction"),rsLoginMissionsAdmin.getDouble("proprietaire_start"),rsLoginMissionsAdmin.getDouble("proprietaire_end"),adress,rsLoginMissionsAdmin.getInt("id_propriete"),rsLoginMissionsAdmin.getInt("id_proprietaire"),rsLoginMissionsAdmin.getString("nom"));
					m.setIdMission(rsLoginMissionsAdmin.getInt("mission_id"));
					
					if(rsLoginMissionsAdmin.getString("nom").equals("waiting") || rsLoginMissionsAdmin.getString("nom").equals("finished"))
					m.addHoraireCleaner(rsLoginMissionsAdmin.getInt("cleaner_start"), rsLoginMissionsAdmin.getInt("cleaner_end"));
					
					admin.addMissions(m);
				}
				
				String strUserAdmin ="SELECT * FROM users;";
				ResultSet rsUserAdmin = stLoginAdmin.executeQuery(strUserAdmin);
		
				while(rsUserAdmin.next()) 
				{
					if(rsUserAdmin.getString("role").equals("Cleaner")) {
						Cleaner user = new Cleaner(rsUserAdmin.getString("first_name"),rsUserAdmin.getString("second_name"),rsUserAdmin.getString("username"),rsUserAdmin.getString("mail"),rsUserAdmin.getString("password"),rsUserAdmin.getInt("age"),rsUserAdmin.getString("bio"),rsUserAdmin.getInt("phone_number"),rsUserAdmin.getString("date_of_birth"),rsUserAdmin.getFloat("note"),rsUserAdmin.getInt("nb_mission"),rsUserAdmin.getInt("perimeter"),rsUserAdmin.getInt("tarif_horaire"));
						user.setDateOfCreation(rsUserAdmin.getDate("date_creation"));
						user.setId(rsUserAdmin.getInt(1));
						admin.addCleaners(user);	
					}
					else if(rsUserAdmin.getString("role").equals("Proprietaire")) {
						
						Proprietaire user = new Proprietaire(rsUserAdmin.getString("first_name"), rsUserAdmin.getString("second_name"),rsUserAdmin.getString("username"),rsUserAdmin.getString("mail"), rsUserAdmin.getString("password"),rsUserAdmin.getInt("age"),  rsUserAdmin.getString("bio"), rsUserAdmin.getInt("phone_number"),rsUserAdmin.getString("date_of_birth"),rsUserAdmin.getFloat("note"));
						user.setDateOfCreation(rsUserAdmin.getDate("date_creation"));
						user.setId(rsUserAdmin.getInt(1));
						admin.addProprietaires(user);	
					}
				}
				String strLitigesAdmin= "SELECT * FROM litige JOIN users ON id_autor=id_user JOIN mission ON id_mission = mission_id JOIN propriete ON id_propriete=propriete_id JOIN statut_mission ON statut = statut_id JOIN statut_litige ON id_statut = idStatut;";
				ResultSet rsLoginLitigesAdmin = stLoginAdmin.executeQuery(strLitigesAdmin);
				while(rsLoginLitigesAdmin.next()) {
					if(rsLoginLitigesAdmin.getString("role").equals("Cleaner")) {
						Cleaner authorc = new Cleaner(rsLoginLitigesAdmin.getString("first_name"),rsLoginLitigesAdmin.getString("second_name"),rsLoginLitigesAdmin.getString("username"),rsLoginLitigesAdmin.getString("mail"),rsLoginLitigesAdmin.getString("password"),rsLoginLitigesAdmin.getInt("age"),rsLoginLitigesAdmin.getString("bio"),rsLoginLitigesAdmin.getInt("phone_number"),rsLoginLitigesAdmin.getString("date_of_birth"),rsLoginLitigesAdmin.getFloat("note"),rsLoginLitigesAdmin.getInt("nb_mission"),rsLoginLitigesAdmin.getInt("perimeter"),rsLoginLitigesAdmin.getInt("tarif_horaire"));
						
						String adress = rsLoginLitigesAdmin.getString("adress")+" "+rsLoginLitigesAdmin.getString("ville")+" "+rsLoginLitigesAdmin.getString("code_postal");
						Mission lmission = new Mission(rsLoginLitigesAdmin.getString("date_mission"),rsLoginLitigesAdmin.getDouble("time_mission"),rsLoginLitigesAdmin.getString("instruction"),rsLoginLitigesAdmin.getDouble("proprietaire_start"),rsLoginLitigesAdmin.getDouble("proprietaire_end"),adress,rsLoginLitigesAdmin.getInt("id_propriete"),rsLoginLitigesAdmin.getInt("id_proprietaire"),rsLoginLitigesAdmin.getString("nom"));
						lmission.addHoraireCleaner(rsLoginLitigesAdmin.getInt("cleaner_start"), rsLoginLitigesAdmin.getInt("cleaner_end"));
						
						Litige litige = new Litige(authorc,rsLoginLitigesAdmin.getString("text_litige"), rsLoginLitigesAdmin.getString("image1"), rsLoginLitigesAdmin.getString("image2"), rsLoginLitigesAdmin.getString("image3"),lmission) ;
						litige.setStatutLitige(rsLoginLitigesAdmin.getString("nomStatut"));
						litige.setId(rsLoginLitigesAdmin.getInt("litige_id"));
						admin.addLitiges(litige);
					}
					if(rsLoginLitigesAdmin.getString("role").equals("Proprietaire")){
						
						Proprietaire authorp = new Proprietaire(rsLoginLitigesAdmin.getString("first_name"), rsLoginLitigesAdmin.getString("second_name"),rsLoginLitigesAdmin.getString("username"),rsLoginLitigesAdmin.getString("mail"), rsLoginLitigesAdmin.getString("password"),rsLoginLitigesAdmin.getInt("age"),  rsLoginLitigesAdmin.getString("bio"), rsLoginLitigesAdmin.getInt("phone_number"),rsLoginLitigesAdmin.getString("date_of_birth"),rsLoginLitigesAdmin.getFloat("note"));
						
						String adress = rsLoginLitigesAdmin.getString("adress")+" "+rsLoginLitigesAdmin.getString("ville")+" "+rsLoginLitigesAdmin.getString("code_postal");
						Mission lmission = new Mission(rsLoginLitigesAdmin.getString("date_mission"),rsLoginLitigesAdmin.getDouble("time_mission"),rsLoginLitigesAdmin.getString("instruction"),rsLoginLitigesAdmin.getDouble("proprietaire_start"),rsLoginLitigesAdmin.getDouble("proprietaire_end"),adress,rsLoginLitigesAdmin.getInt("id_propriete"),rsLoginLitigesAdmin.getInt("id_proprietaire"),rsLoginLitigesAdmin.getString("nom"));
						lmission.addHoraireCleaner(rsLoginLitigesAdmin.getInt("cleaner_start"), rsLoginLitigesAdmin.getInt("cleaner_end"));
						
						Litige litige = new Litige(authorp, rsLoginLitigesAdmin.getNString("text_litige"), rsLoginLitigesAdmin.getNString("image1"), rsLoginLitigesAdmin.getNString("image2"), rsLoginLitigesAdmin.getNString("image3"),lmission) ;
						litige.setStatutLitige(rsLoginLitigesAdmin.getString("nomStatut"));
						litige.setId(rsLoginLitigesAdmin.getInt("litige_id"));
						admin.addLitiges(litige);
					}
				}
				
				//Changer la redirection admin
				HttpSession session = request.getSession();
				session.setAttribute("user", admin);
				
				getServletContext().getRequestDispatcher("/admin").forward(request,response);
			}
			
		}
			bdd.disconnect();
		}
		catch(SQLException e) {
			System.err.println("Erreur");  e.printStackTrace();
		}
		if(isFailed) {
			getServletContext().getRequestDispatcher("/connection").forward(request,response);
			System.out.println("Connction échoué");
		}
	}

}
