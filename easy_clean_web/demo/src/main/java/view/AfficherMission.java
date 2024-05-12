package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.*;

@WebServlet(name = "AfficherMission", urlPatterns = { "/afficherMission" })
public class AfficherMission extends HttpServlet {

    public AfficherMission() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Recherche mission");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String missionIdStr = request.getParameter("missionId");
        System.out.println("missionIdStr: " + missionIdStr); // Debugging line

        if (missionIdStr != null) {
            int missionId = Integer.parseInt(missionIdStr);
            System.out.println("missionId: " + missionId); // Debugging line

            DAOacces dao = new DAOacces("easy_clean", "toto", "titi");
            Mission mission = null;
            try {
                String query = "SELECT m.date_mission, m.time_mission, m.instruction, m.proprietaire_start, m.proprietaire_end, "
                        + "p.adress, m.id_propriete, m.id_proprietaire, m.statut, m.mission_id "
                        + "FROM Mission m "
                        + "JOIN propriete p ON m.id_propriete = p.propriete_id "
                        + "WHERE m.mission_id = ?";
                PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, missionId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Mission found in database."); // Debugging line
                    mission = new Mission(
                            resultSet.getString("date_mission"),
                            resultSet.getDouble("time_mission"),
                            resultSet.getString("instruction"),
                            resultSet.getDouble("proprietaire_start"),
                            resultSet.getDouble("proprietaire_end"),
                            resultSet.getString("adress"),
                            resultSet.getInt("id_propriete"),
                            resultSet.getInt("id_proprietaire"),
                            resultSet.getString("statut"));
                    mission.setIdMission(resultSet.getInt("mission_id"));
                }

                if (mission != null) {
                    System.out.println("Mission is not null. Preparing to display."); // Debugging line
                    try (PrintWriter out = response.getWriter()) {
                        out.append("<html>"
                                + "<head>"
                                + "<title>Détails de la mission</title>"
                                + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>"
                                + "</head>"
                                + "<body>"
                                + "<div class='container mt-3'>"
                                + "<h2>Détails de la mission</h2>"
                                + "<table class='table table-bordered'>"
                                + "<tr><th>Address</th><td>" + mission.getAdress() + "</td></tr>"
                                + "<tr><th>Date</th><td>" + mission.getDate() + "</td></tr>"
                                + "<tr><th>Duration</th><td>" + String.valueOf(mission.getDuration()) + "</td></tr>"
                                + "<tr><th>Instructions</th><td>" + mission.getInstruction() + "</td></tr>"
                                + "<tr><th>Status</th><td>" + mission.getStatut() + "</td></tr>"
                                + "</table>"
                                + "<form action='/postulerMission' method='POST'>"
                                + "<input type='hidden' name='missionId' value='" + mission.getIdMission() + "'>"
                                + "<button type='submit' class='btn btn-primary'>Postuler</button>"
                                + "</form>"
                                + "</div>"
                                + "</body>"
                                + "</html>");
                    }
                } else {
                    System.out.println("Mission is null."); // Debugging line
                    try (PrintWriter out = response.getWriter()) {
                        out.print("Mission non trouvée.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.getMessage()); // Debugging line
                e.printStackTrace();
            }
        } else {
            System.out.println("missionIdStr is null."); // Debugging line
            try (PrintWriter out = response.getWriter()) {
                out.print("Aucun identifiant de mission fourni.");
            }
        }
    }
}
