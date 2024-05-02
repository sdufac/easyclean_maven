package controller;

import java.io.IOException;
import java.util.SortedMap;

import model.Cleaner;
import model.Mission;
import model.Utilisateur;

public class ControleurAfficheMission {
    private Cleaner user;

    public void AfficherMission(Cleaner user, SortedMap<Double, Mission> resultMission, double range,
            String adressCleaner) {
        this.user = user;
        // Boucle qui boucle sur chaque mission du tableau
        for (SortedMap.Entry<Double, Mission> entry : resultMission.entrySet()) {
            if (entry.getKey() < range) {

            }
        }
    }
}
