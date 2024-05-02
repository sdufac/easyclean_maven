package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cleaner;
import model.DAOacces;
import model.Mission;

public class ControllerMissionInfo {
	Mission m;
	Cleaner user;
	String adressCleaner;
	double horaireStart;
	double horaireEnd;
	double salary;

	public void afficherMissionInfo(Mission m, Cleaner user, String adress) {
		this.m = m;
		this.user = user;
		this.adressCleaner = adress;
		this.salary = user.calculSalary(m.getDuration());

		System.out.println(user.getSalaryPerHour());

	}

	public void postuler() throws IOException {
		try {
			DAOacces bdd = new DAOacces("easy_clean", "root", "");
			String strQuery = "INSERT INTO postulation(idMission,idCleaner,horaireStart,horaireEnd,salaireCleaner) VALUES("
					+ m.getIdMission() + "," + user.getId() + "," + debutHoraire + "," + finHoraire + "," + this.salary
					+ ");";

			String strPostulation = "SELECT * FROM postulation JOIN mission ON idMission = mission_id JOIN statut_mission ON statut=statut_id JOIN propriete ON id_propriete = propriete_id WHERE idCleaner ="
					+ user.getId() + " AND idMission = " + m.getIdMission() + ";";
			Statement stPostulation = bdd.getConnection().createStatement();
			bdd.getConnection().createStatement().executeUpdate(strQuery);
			ResultSet rsPostulation = stPostulation.executeQuery(strPostulation);

			while (rsPostulation.next()) {
				String adress = rsPostulation.getString("adress") + " " + rsPostulation.getString("ville") + " "
						+ rsPostulation.getString("code_postal");
				Mission m = new Mission(rsPostulation.getString("date_mission"),
						rsPostulation.getDouble("time_mission"), rsPostulation.getString("instruction"),
						rsPostulation.getDouble("proprietaire_start"), rsPostulation.getDouble("proprietaire_end"),
						adress, rsPostulation.getInt("id_proprietaire"), user.getId(), rsPostulation.getString("nom"));
				m.setIdMission(rsPostulation.getInt("mission_id"));

				user.getPostulation().add(m);
			}
			bdd.disconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
