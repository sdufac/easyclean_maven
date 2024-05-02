package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cleaner;
import model.HTMLfunction;
import model.Mission;
import model.Postulation;
import model.Proprietaire;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AccueilCleaner
 */
@WebServlet(name = "VueMission", urlPatterns = { "/vuemission" })
public class VueMission extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VueMission() {
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
		int idMission = Integer.valueOf(request.getParameter("idMission"));
        HttpSession session = request.getSession();
        Proprietaire user = (Proprietaire)session.getAttribute("user");
		
		String html = "";

		Mission mission = null;
        for(Mission m : user.getMissions()){
            if(m.getIdMission()==idMission){
                mission = m;
            }
        }

		//Gestion des postulations
		if(mission.getStatut().equals("available")){
			html = "<h2>Postulations</h2><hr><table>";
			int pcount = 0;

			for(Postulation p : user.getPostulation()){
				if(p.getMission().getIdMission()== mission.getIdMission()){
					html = html + "<tr><th>" + p.getCleaner().getFirstName() + "</th><th>" + p.getCleaner().getSecondName() + "</th><th>" + p.getHoraireStart() +"-"+ p.getHoraireEnd() + "</th><th>" + p.getSalaireCleaner()+"</th>"
					+"<th><form method=\"POST\" action=\"controllerpostulation\">"
					+"<input type=\"hidden\" name=\"idpostulation\" value=\""+p.getId()+"\"/>"
					+"<input type=\"submit\" value=\"Accepter\"/></form></th>"
					+ "</tr>";
					pcount++;
				}
				
			}
			
			html = html + "</table>";

			if(pcount == 0){
				html = "<h2>Postulations</h2><hr>Pas encore de postulation";
			}
		}
		//Gestion du cleaner ayant participé à la mission
		else if(mission.getCleaner() != null && mission.getStatut().equals("waiting") || mission.getStatut().equals("finished") || mission.getStatut().equals("cleanerFinished")){
			Cleaner c = mission.getCleaner();
			html = "<h2>Cleaner</h2><hr>" +
			c.getFirstName() +" "+c.getSecondName();
		}

		if(mission.getStatut().equals("cleanerFinished")){
			html = html + "<br><br><form method=\"post\" action=\"finmission\"><input type=\"hidden\" name=\"idmission\" value=\""+mission.getIdMission()+"\"/><input type=\"submit\" value=\"Confirmer la fin de la mission\"/></form>";
		}

        HTMLfunction.vueMission(mission, response.getWriter());
        PrintWriter out = response.getWriter();
		out.append(html);
        out.append("<br><button type=\"button\" onclick=\"location.href='proprietaire'\">Retour</button>");
	}

}
