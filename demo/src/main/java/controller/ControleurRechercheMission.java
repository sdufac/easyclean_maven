/*package controller;

import java.sql.ResultSet;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import model.*;

public class ControleurRechercheMission {

    private String dbName = "easy_clean";
    private String login = "root";
    private String password = "";
    // private String strUrl;
    SortedMap<Double, Mission> ResultMission = new TreeMap<>();

    public ControleurRechercheMission(Cleaner user, String address, double d) {

        DAOacces dao = new DAOacces(dbName, login, password);
        try {
            String strQuery = "SELECT * FROM Mission WHERE statut = '1';";
            ResultSet rsLogin = dao.getStLogin().executeQuery(strQuery);
            while (rsLogin.next()) {
                int idMission = rsLogin.getInt("mission_id");
                int idPropriete = rsLogin.getInt("id_propriete");
                System.out.println("Mission ID: " + idMission + " id " + idPropriete);

                String adressCleaner = GeocodingService.getAdresseCleaner(user.getId());
                System.out.println("ID clean :" + user.getId());
                String adressPropriete = GeocodingService.getAdressepropriete(idPropriete);

                System.out.println("adresse1: " + adressCleaner + " adresse2: " + adressPropriete);

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
                Mission m = new Mission(rsLogin.getString(2), rsLogin.getDouble(3), rsLogin.getString(4),
                        rsLogin.getDouble(10), rsLogin.getDouble(11), adressPropriete, rsLogin.getInt(7), user.getId(),
                        rsLogin.getString("statut"));
                m.setIdMission(idMission);

                ResultMission.put(distance, m);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
        }
        for (double key : ResultMission.keySet()) {
            System.out.println(key + "/" + ResultMission.get(key));
        }

    }

    public SortedMap<Double, Mission> getResultMission() {
        return ResultMission;
    }

}*/
