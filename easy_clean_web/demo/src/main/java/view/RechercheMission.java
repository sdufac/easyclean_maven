package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import model.Cleaner;
import java.util.SortedMap;

import controller.ControleurRechercheMission;
import model.Mission;

@WebServlet(name = "RechercheMission", urlPatterns = { "/rechercheMission" })
public class RechercheMission extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalcode");
        String completeAddress = street + ", " + city + ", " + postalCode;
        Double maxDistance;

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

            String address = request.getParameter("address");
            double maxDistance = Double.parseDouble(request.getParameter("maxDistance"));
            Cleaner user = (Cleaner) session.getAttribute("user");

            ControleurRechercheMission controleur = new ControleurRechercheMission(user, completeAddress, maxDistance));
            SortedMap<Double, Mission> resultMission = controleur.getResultMission();

            session.setAttribute("resultMission", resultMission);
            out.print(resultMission);
        }
    }
}
