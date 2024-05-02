package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import model.Cleaner;
import model.HTMLfunction;
import model.Mission;
import controller.ControleurRechercheMission;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Missiontrouve", urlPatterns = { "/rechercheMission" })

public class Missiontrouve extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalcode");
        int range = Integer.parseInt(request.getParameter("range"));

        ControleurRechercheMission controleur = new ControleurRechercheMission();

        List<Mission> missions = AjoutMission(session.getAttribute("user"), address, city, postalCode, range);

        controleur.AjoutMission(null, address, city, postalCode, range);

        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='src/main/webapp/WEB-INF/cssApp.css'>");
        out.println("</head><body>");

        out.println("<h3>Missions disponibles dans la zone spécifiée :</h3>");
        out.println(HTMLfunction.tabMission((ArrayList<Mission>) missions));

        out.println("</body></html>");
    }

    private List<Mission> AjoutMission(Object attribute, String address, String city, String postalCode, int range) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'AjoutMission'");
    }

}
