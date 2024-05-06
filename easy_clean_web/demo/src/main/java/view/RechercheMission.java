package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import model.Cleaner;
import java.util.SortedMap;

import controller.ControleurRechercheMission;
import model.Mission;

@WebServlet(name = "RechercheMission", urlPatterns = { "/rechercheMission" })
public class RechercheMission extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String address = request.getParameter("address");
        double maxDistance = Double.parseDouble(request.getParameter("maxDistance"));
        Cleaner user = (Cleaner) session.getAttribute("user");

        // Effectuer la recherche de missions dans la zone maximale définie
        ControleurRechercheMission controleur = new ControleurRechercheMission(user, address, maxDistance);
        SortedMap<Double, Mission> resultMission = controleur.getResultMission();

        // Enregistrer les résultats dans la session pour les utiliser dans la vue
        session.setAttribute("resultMission", resultMission);
    }
}
