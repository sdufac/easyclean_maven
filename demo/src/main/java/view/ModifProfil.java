package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cleaner;
import model.HTMLfunction;
import model.Utilisateur;
import model.Mission;

@WebServlet(name = "ModifProfil", urlPatterns = { "/ModifProfil" })
public class ModifProfil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifProfil() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");

        // Ajout de logs de débogage pour vérifier les missions récupérées
        System.out.println("User: " + user.getUsername());
        ArrayList<Mission> missions = user.getMissions();
        for (Mission mission : missions) {
            System.out.println("Mission: " + mission.getProperty().getAdress() + ", Statut: " + mission.getStatut());
        }

        String[] tabMissions = HTMLfunction.proprioTabMission(missions, null, null);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.append("<html>"
                    + "<head>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>"
                    + "<link rel='stylesheet' href='src/main/webapp/WEB-INF/cssApp.css'>"
                    + "<title>Profil de l'utilisateur</title>"
                    + "<style>"
                    + "#container { display: flex; }"
                    + "#profilcontainer { flex: 1; border-right: 1px solid black; padding: 10px; }"
                    + "#mission { flex: 2; padding: 10px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div id='container'>");

            HTMLfunction.profilUser(out, user);

            out.append("<div id='mission' class='container mt-3'>"
                    + "<h3>Vos missions en attente</h3>"
                    + tabMissions[0]
                    + "<hr>"
                    + "<h3>Vos missions en cours</h3>"
                    + tabMissions[1]
                    + "<hr>"
                    + "<h3>Votre historique de missions</h3>"
                    + tabMissions[2]
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>");

            System.out.println("Tab Available: " + tabMissions[0]);
            System.out.println("Tab Waiting: " + tabMissions[1]);
            System.out.println("Tab Finished: " + tabMissions[2]);
        }
    }
}
