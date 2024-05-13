package controller;

import java.sql.ResultSet;
import org.json.JSONObject;

import java.sql.SQLException;
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
            String strQuery = "SELECT * FROM Mission JOIN users ON id_proprietaire=id_user JOIN propriete ON id_propriete=propriete_id WHERE statut = '1';";
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
                String adress = rsLogin.getString("adress")+" "+rsLogin.getInt("code_postal")+" "+rsLogin.getString("ville");
                    Property property = new Property(rsLogin.getInt("propriete_id"), adress,rsLogin.getInt("code_entrer"),rsLogin.getInt("surface"));

                    Proprietaire proprio = new Proprietaire(rsLogin.getString("first_name"), rsLogin.getString("second_name"),rsLogin.getString("username"),rsLogin.getString("mail"), rsLogin.getString("password"),rsLogin.getInt("age"),  rsLogin.getString("bio"), rsLogin.getInt("phone_number"),rsLogin.getString("date_of_birth"),rsLogin.getFloat("note"));
                    proprio.setDateOfCreation(rsLogin.getDate("date_creation"));
					proprio.setId(rsLogin.getInt("id_user"));

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
        for (double key : ResultMission.keySet()) {
            System.out.println(key + "/" + ResultMission.get(key));
        }

    }

    public SortedMap<Double, Mission> getResultMission() {
        return ResultMission;
    }

}
