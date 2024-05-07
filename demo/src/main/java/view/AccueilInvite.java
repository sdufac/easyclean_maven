package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HTMLfunction;
import model.Mission;
import model.DAOacces;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class AccueilInvite
 */
@WebServlet(name = "AccueilInvite" , urlPatterns = {"/invite"})
public class AccueilInvite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AccueilInvite() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Mission> missionTab = new ArrayList<Mission>();

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		HTMLfunction.head(response.getWriter());
		DAOacces bdd = new DAOacces("easy_clean","root","");
		System.out.println(bdd.getDbName());
		try {
			String strMissions = "SELECT * FROM mission JOIN statut_mission ON statut=statut_id JOIN propriete ON id_propriete = propriete_id";
			//Statement stMissions = bdd.getConnection().createStatement();
			ResultSet rsMissions = bdd.getStLogin().executeQuery(strMissions);
			
			while(rsMissions.next()) {
				String adress = rsMissions.getString("adress")+" "+rsMissions.getString("ville")+" "+rsMissions.getString("code_postal");
				Mission m = new Mission(rsMissions.getString("date_mission"),rsMissions.getDouble("time_mission"),rsMissions.getString("instruction"),rsMissions.getDouble("proprietaire_start"),rsMissions.getDouble("proprietaire_end"),adress,rsMissions.getInt("id_proprietaire"),1,rsMissions.getString("nom"));
				m.setIdMission(rsMissions.getInt("mission_id"));
				missionTab.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		response.getWriter().append("<body><h2>Accueil Invité</h2><button type=\"button\" onclick=\"location.href='connection'\"/>Se connecter</button> <button type=\"button\" onclick=\"location.href='createaccount'\"/>Créer un compte</button><hr>");
		response.getWriter().append(HTMLfunction.tabMission(missionTab));
		response.getWriter().append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
