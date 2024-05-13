package view;

import javax.servlet.RequestDispatcher;
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
                        + "JOIN propriete p ON m.id_propriete = p.propriete_id JOIN users ON id_proprietaire = id_user"
                        + "WHERE m.mission_id = ?";
                PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, missionId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String adress = resultSet.getString("adress")+" "+resultSet.getInt("code_postal")+" "+resultSet.getString("ville");
                    Property property = new Property(resultSet.getInt("propriete_id"), adress,resultSet.getInt("code_entrer"),resultSet.getInt("surface"));

                    Proprietaire proprio = new Proprietaire(resultSet.getString("first_name"), resultSet.getString("second_name"),resultSet.getString("username"),resultSet.getString("mail"), resultSet.getString("password"),resultSet.getInt("age"),  resultSet.getString("bio"), resultSet.getInt("phone_number"),resultSet.getString("date_of_birth"),resultSet.getFloat("note"));
                    proprio.setDateOfCreation(resultSet.getDate("date_creation"));
					proprio.setId(resultSet.getInt("id_user"));

                    mission = new Mission(
                            resultSet.getString("date_mission"),
                            resultSet.getDouble("time_mission"),
                            resultSet.getString("instruction"),
                            resultSet.getDouble("proprietaire_start"),
                            resultSet.getDouble("proprietaire_end"),
                            proprio,
                            property,
                            resultSet.getString("statut"));
                    mission.setIdMission(resultSet.getInt("mission_id"));

                }

                if (mission != null) {
                    try (PrintWriter out = response.getWriter()) {
                        // Assuming you have a way to get the user ID from the session or another source
                        String userId = String.valueOf(((Cleaner) request.getSession().getAttribute("user")).getId());
                        out.append("<html>"
                                + "<head>"
                                + "<title>Détails de la mission</title>"
                                + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>"
                                + "</head>"
                                + "<body>"
                                + "<div class='container mt-3'>"
                                + "<h2>Détails de la mission</h2>"
                                + "<table class='table table-bordered'>"
                                + "<tr><th>Address</th><td>" + mission.getProperty().getAdress() + "</td></tr>"
                                + "<tr><th>Date</th><td>" + mission.getDate() + "</td></tr>"
                                + "<tr><th>Duration</th><td>" + String.valueOf(mission.getDuration()) + "</td></tr>"
                                + "<tr><th>Instructions</th><td>" + mission.getInstruction() + "</td></tr>"
                                + "<tr><th>Status</th><td>" + mission.getStatut() + "</td></tr>"
                                + "</table>"
                                + "<form action='controleurPostulerMission' method='POST'>"
                                + "<input type='hidden' name='missionId' value='" + mission.getIdMission() + "'>"
                                + "<input type='hidden' name='userId' value='" + userId + "'>"
                                + "<div class='form-group'>"
                                + "<label for='horaireStart'>Horaire de début :</label>"
                                + "<input type='number' class='form-control' id='horaireStart' name='horaireStart' step='0.1' required>"
                                + "</div>"
                                + "<div class='form-group'>"
                                + "<label for='horaireEnd'>Horaire de fin :</label>"
                                + "<input type='number' class='form-control' id='horaireEnd' name='horaireEnd' step='0.1' required>"
                                + "</div>"
                                + "<div class='form-group'>"
                                + "<label for='salaireCleaner'>Salaire :</label>"
                                + "<input type='number' class='form-control' id='salaireCleaner' name='salaireCleaner' step='0.01' required>"
                                + "</div>"
                                + "<button type='submit' class='btn btn-primary'>Postuler</button>"
                                + "</form>"
                                + "</div>"
                                + "</body>"
                                + "</html>");
                    }
                } else {
                    System.out.println("Mission is null.");
                    try (PrintWriter out = response.getWriter()) {
                        out.print("Mission non trouvée.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("missionIdStr is null.");
            try (PrintWriter out = response.getWriter()) {
                out.print("Aucun identifiant de mission fourni.");
            }
        }
    }
}
