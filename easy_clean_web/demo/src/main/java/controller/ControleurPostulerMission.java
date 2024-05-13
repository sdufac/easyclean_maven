package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.*;

@WebServlet(name = "ControleurPostulerMission", urlPatterns = { "/controleurPostulerMission" })
public class ControleurPostulerMission extends HttpServlet {

    public ControleurPostulerMission() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String missionIdStr = request.getParameter("missionId");
        String userIdStr = request.getParameter("userId");
        String horaireStart = request.getParameter("horaireStart");
        String horaireEnd = request.getParameter("horaireEnd");
        String salaireCleanerStr = request.getParameter("salaireCleaner");

        System.out.println("missionIdStr: " + missionIdStr);
        System.out.println("userIdStr: " + userIdStr);
        System.out.println("horaireStart: " + horaireStart);
        System.out.println("horaireEnd: " + horaireEnd);
        System.out.println("salaireCleanerStr: " + salaireCleanerStr);

        if (missionIdStr != null && userIdStr != null && horaireStart != null && horaireEnd != null
                && salaireCleanerStr != null) {
            int missionId = Integer.parseInt(missionIdStr);
            int userId = Integer.parseInt(userIdStr);
            double salaireCleaner = Double.parseDouble(salaireCleanerStr);

            DAOacces dao = new DAOacces("easy_clean", "toto", "titi");
            try {
                Connection conn = dao.getConnection();
                String query = "INSERT INTO postulation (idMission, idCleaner, horaireStart, horaireEnd, salaireCleaner) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, missionId);
                preparedStatement.setInt(2, userId);
                preparedStatement.setString(3, horaireStart);
                preparedStatement.setString(4, horaireEnd);
                preparedStatement.setDouble(5, salaireCleaner);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    getServletContext().getRequestDispatcher("/cleaner").forward(request, response);
                } else {
                    response.getWriter().println("Échec de la postulation.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Erreur lors de la postulation : " + e.getMessage());
            }
        } else {
            response.getWriter().println("Tous les champs sont requis.");
        }
    }
}
