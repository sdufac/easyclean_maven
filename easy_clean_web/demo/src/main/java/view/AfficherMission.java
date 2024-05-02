package view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import model.*;
import controller.*;

@WebServlet(name = "AfficherMission", urlPatterns = { "/rechercheMission" })
public class AfficherMission extends HttpServlet {

    /* @see HttpServlet#HttpServlet() */

    public AfficherMission() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Recherche mission");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String address = request.getParameter("address");
        String rangeValue = request.getParameter("myRange");

        Cleaner user = (Cleaner) request.getSession().getAttribute("user");

        ControleurRechercheMission controller = new ControleurRechercheMission(user, address,
                Double.parseDouble(rangeValue));

        // Récupérer les missions trouvées
        SortedMap<Double, Mission> missions = controller.getResultMission();

        // Préparer les données pour l'affichage
        request.setAttribute("missions", missions);

        // Transférer le contrôle à une JSP ou un autre servlet qui affichera les
        // résultats
        RequestDispatcher dispatcher = request.getRequestDispatcher("chemin/vers/la/vue.jsp");
        dispatcher.forward(request, response);

        try (PrintWriter out = response.getWriter()) {
            out.append("<h2>Liste des missions</h2>");

            if (missions != null && !missions.isEmpty()) {
                for (Double key : missions.keySet()) {
                    Mission mission = missions.get(key);
                    out.append("<div>");
                    out.append("<p>Mission: ").append(mission.getInstruction()).append("</p>");
                    out.append("<p>Distance: ").append(String.valueOf(key)).append(" km</p>");
                    // Ajoutez plus de détails selon ce que vous avez dans l'objet Mission
                    out.append("</div>");
                }
            } else {
                out.append("<p>Aucune mission trouvée.</p>");
            }

            out.append("</body>");
            out.append("</html>");
        }
    }
}
