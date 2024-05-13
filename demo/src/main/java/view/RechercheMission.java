package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cleaner;
import model.DAOacces;
import model.GeocodingService;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import controller.ControleurRechercheMission;
import model.Mission;
import model.Property;
import model.Proprietaire;

@WebServlet(name = "RechercheMission", urlPatterns = { "/rechercheMission" })
public class RechercheMission extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Concatene les données du formulaire en une seule adresse
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String postalCode = request.getParameter("postalcode");
            String completeAddress = street + ", " + city + ", " + postalCode;

            HttpSession session = request.getSession();
            Cleaner user = (Cleaner) session.getAttribute("user");

            try {

                JSONObject coordonnees = GeocodingService.obtenirCoordonneesPropriete(completeAddress);
                if (coordonnees != null) {
                    double lat = coordonnees.getDouble("lat");
                    double lon = coordonnees.getDouble("lon");

                    double maxDistance = Double.parseDouble(request.getParameter("maxDistance"));

                    SortedMap<Double, Mission> resultRecherche = SortMission(user, completeAddress);

                    session.setAttribute("resultMission", resultRecherche);
                    out.append("<html>"
                            + "<head>"
                            + "<title>Liste des missions</title>"
                            + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>"
                            + "</head>"
                            + "<body>"
                            + "<div class='container mt-3'>"
                            + "<h2>Liste des missions</h2>"
                            + "<table class='table table-bordered'>"
                            + "<thead>"
                            + "<tr>"
                            + "<th>Address</th>"
                            + "<th>Date</th>"
                            + "<th>Duration</th>"
                            + "<th>Instructions</th>"
                            + "<th>Status</th>"
                            + "<th>Action</th>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>");

                    for (Map.Entry<Double, Mission> missionEntry : resultRecherche.entrySet()) {
                        if (missionEntry.getKey() < maxDistance) {
                            Mission mission = missionEntry.getValue();
                            out.append("<tr>"
                                    + "<td>" + mission.getProperty().getAdress() + "</td>"
                                    + "<td>" + mission.getDate() + "</td>"
                                    + "<td>" + String.valueOf(mission.getDuration()) + "</td>"
                                    + "<td>" + mission.getInstruction() + "</td>"
                                    + "<td>" + mission.getStatut() + "</td>"
                                    + "<td>"
                                    + "<form name='voirMission' method='POST' action='afficherMission' style='margin:0;'>"
                                    + "<input type='hidden' name='missionId' value='" + mission.getIdMission() + "'>"
                                    + "<input type='submit' value='Voir' class='btn btn-primary'>"
                                    + "</form>"
                                    + "</td>"
                                    + "</tr>");
                        }
                    }

                    out.append("</tbody>"
                            + "</table>"
                            + "</div>"
                            + "</body>"
                            + "</html>");

                } else {
                    out.print("Coordonnées non trouvées.");
                }
            } catch (Exception e) {
                out.print("Erreur lors de la recherche des coordonnées: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public SortedMap<Double, Mission> SortMission(Cleaner user, String address) {
        SortedMap<Double, Mission> ResultMission = new TreeMap<>();
        DAOacces dao = new DAOacces("easy_clean", "toto", "titi");
        try {
            String strQuery = "SELECT * FROM Mission JOIN users ON id_proprietaire=id_user JOIN propriete ON id_propriete=propriete_id WHERE statut = '1';";
            ResultSet rsLogin = dao.getStLogin().executeQuery(strQuery);
            while (rsLogin.next()) {
                int idMission = rsLogin.getInt("mission_id");
                int idPropriete = rsLogin.getInt("id_propriete");

                String adress = rsLogin.getString("adress")+" "+rsLogin.getInt("code_postal")+" "+rsLogin.getString("ville");
                Property property = new Property(rsLogin.getInt("propriete_id"), adress,rsLogin.getInt("code_entrer"),rsLogin.getInt("surface"));

                Proprietaire proprio = new Proprietaire(rsLogin.getString("first_name"), rsLogin.getString("second_name"),rsLogin.getString("username"),rsLogin.getString("mail"), rsLogin.getString("password"),rsLogin.getInt("age"),  rsLogin.getString("bio"), rsLogin.getInt("phone_number"),rsLogin.getString("date_of_birth"),rsLogin.getFloat("note"));
                proprio.setDateOfCreation(rsLogin.getDate("date_creation"));
				proprio.setId(rsLogin.getInt("id_user"));

                String adressCleaner = address;
                String adressPropriete = GeocodingService.getAdressepropriete(idPropriete);

                JSONObject coordonneesCleaner = GeocodingService.obtenirCoordonneesCleaner(adressCleaner);
                JSONObject coordonnesPropriete = GeocodingService.obtenirCoordonneesPropriete(adressPropriete);

                System.out.println("Lat long 1: " + coordonneesCleaner.getDouble("lat") + " " +
                        coordonneesCleaner.getDouble("lon") + "Lat long 2: " + coordonnesPropriete.getDouble("lat")
                        + " " +
                        coordonnesPropriete.getDouble("lon"));

                double distance = GeocodingService.calculerDistance(coordonneesCleaner.getDouble("lat"),
                        coordonneesCleaner.getDouble("lon"), coordonnesPropriete.getDouble("lat"),
                        coordonnesPropriete.getDouble("lon"));

                System.out.println("Distance: " + distance);
                Mission m = new Mission(
                    rsLogin.getString("date_mission"),
                    rsLogin.getDouble("time_mission"),
                    rsLogin.getString("instruction"),
                    rsLogin.getDouble("proprietaire_start"),
                    rsLogin.getDouble("proprietaire_end"),
                    proprio,
                    property,
                    rsLogin.getString("statut"));
            m.setIdMission(rsLogin.getInt("mission_id"));

                ResultMission.put(distance, m);

            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
        }
        return ResultMission;
    }
}
