package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import model.*;

public class ControleurAfficheMission {
    private Cleaner user;
    private String dbName = "easy_clean";
    private String login = "toto";
    private String password = "titi";

    SortedMap<Double, Mission> resultMission = new TreeMap<>();

    public void afficherMission(Cleaner user, double range, String adressCleaner) {
        DAOacces dao = new DAOacces(dbName, login, password);
        try {
            List<Mission> missionsTrouvees = new ArrayList<>();
            String query = "SELECT * FROM Mission WHERE statut = 'available';";
            ResultSet resultSet = dao.getStLogin().executeQuery(query);

            while (resultSet.next()) {
                String date = resultSet.getString("date_mission");
                double duration = resultSet.getDouble("time_mission");
                String instruction = resultSet.getString("instruction");
                double limitStart = resultSet.getDouble("proprietaire_start");
                double limitEnd = resultSet.getDouble("proprietaire_end");
                String address = resultSet.getString("adress");
                int id_propriete = resultSet.getInt("id_propriete");
                int idProprio = resultSet.getInt("id_proprietaire");
                String statut = resultSet.getString("statut");

                Mission mission = new Mission(date, duration, instruction, limitStart, limitEnd, address, id_propriete,
                        idProprio, statut);

                missionsTrouvees.add(mission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
