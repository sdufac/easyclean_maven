package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HTMLfunction;
import model.Mission;
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

        Mission mission = null;
        for(Mission m : user.getMissions()){
            if(m.getIdMission()==idMission){
                mission = m;
            }
        }

        HTMLfunction.vueMission(mission, response.getWriter());
        PrintWriter out = response.getWriter();
        out.append("<br><button type=\"button\" onclick=\"location.href='proprietaire'\">Retour</button>");
	}

}
