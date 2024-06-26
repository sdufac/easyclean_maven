package model;

import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class HTMLfunction {

	public static void head(PrintWriter out) {
		out.append("<!DOCTYPE html>"
				+ "<html lang='fr'>"
				+ "<head>"
				+ "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "<title> " + "title" + "</title>"
				+ "</head>");
	}

	public static String tabMission(ArrayList<Mission> mtab) {
		String html = "<table>";
		for (Mission m : mtab) {
			html = html + "<tr>";
			html = html + "<th>" + m.getProperty().getAdress() + "</th>";
			html = html + "<th>" + m.getDate() + "</th>";
			html = html + "<th>" + m.getInstruction() + "</th>";
			html = html + "</tr>";
		}
		html = html + "</table>";

		return html;
	}

	public static String[] proprioTabMission(ArrayList<Mission> mtab, ArrayList<Postulation> ptab, Proprietaire user) {
		String[] stringTab;
		stringTab = new String[3];

		String tabAvailable = "<table>";
		String tabWaiting = "<table>";
		String tabFinished = "<table>";

		int availableCount = 0;
		int waitingCount = 0;
		int finishedCount = 0;

		for (Mission m : mtab) {
			if (m.getStatut().equals("available")) {
				int pCount = 0;
				for (Postulation p : ptab) {
					if (p.getMission().getIdMission() == m.getIdMission()) {
						pCount++;
					}
				}

				tabAvailable = tabAvailable + "<tr>";
				tabAvailable = tabAvailable + "<th>" + m.getProperty().getAdress() + "</th>";
				tabAvailable = tabAvailable + "<th>" + m.getDate() + "</th>";
				tabAvailable = tabAvailable + "<th>" + m.getInstruction() + "</th>";
				tabAvailable = tabAvailable
						+ "<th><form action=\"vuemission\" method=\"POST\"><input type=\"hidden\" name=\"idMission\" value=\""
						+ m.getIdMission() + "\"><input type=\"submit\" value=\"Voir\"></form></th>";
				tabAvailable = tabAvailable + "<th>(" + pCount + ")</th>";
				tabAvailable = tabAvailable + "</tr>";

				availableCount++;
			} else if (m.getStatut().equals("waiting")) {
				tabWaiting = tabWaiting + "<tr>";
				tabWaiting = tabWaiting + "<th>" + m.getProperty().getAdress() + "</th>";
				tabWaiting = tabWaiting + "<th>" + m.getDate() + "</th>";
				tabWaiting = tabWaiting + "<th>" + m.getInstruction() + "</th>";
				tabWaiting = tabWaiting
						+ "<th><form action=\"vuemission\" method=\"POST\"><input type=\"hidden\" name=\"idMission\" value=\""
						+ m.getIdMission() + "\"><input type=\"submit\" value=\"Voir\"></form></th>";
				tabWaiting = tabWaiting + "</tr>";

				waitingCount++;
			} else if (m.getStatut().equals("cleanerFinished")) {
				tabWaiting = tabWaiting + "<tr>";
				tabWaiting = tabWaiting + "<th>" + m.getProperty().getAdress() + "</th>";
				tabWaiting = tabWaiting + "<th>" + m.getDate() + "</th>";
				tabWaiting = tabWaiting + "<th>" + m.getInstruction() + "</th>";
				tabWaiting = tabWaiting
						+ "<th><form action=\"vuemission\" method=\"POST\"><input type=\"hidden\" name=\"idMission\" value=\""
						+ m.getIdMission() + "\"><input type=\"submit\" value=\"Voir\"></form></th>";
				tabWaiting = tabWaiting + "<th>Terminé<th>";
				tabWaiting = tabWaiting + "</tr>";

				waitingCount++;
			} else if (m.getStatut().equals("finished")) {
				tabFinished = tabFinished + "<tr>";
				tabFinished = tabFinished + "<th>" + m.getProperty().getAdress() + "</th>";
				tabFinished = tabFinished + "<th>" + m.getDate() + "</th>";
				tabFinished = tabFinished + "<th>" + m.getInstruction() + "</th>";
				tabFinished = tabFinished
						+ "<th><form action=\"vuemission\" method=\"POST\"><input type=\"hidden\" name=\"idMission\" value=\""
						+ m.getIdMission() + "\"><input type=\"submit\" value=\"Voir\"></form></th>";
				if (user.checkMissionLitige(m.getIdMission()) != null) {
					if (user.checkMissionLitige(m.getIdMission()).getStatutLitige().equals("accepte"))
						tabFinished = tabFinished + "<th>Litige accepté</th>";
					else if (user.checkMissionLitige(m.getIdMission()).getStatutLitige().equals("refuse"))
						tabFinished = tabFinished + "<th>Litige refusé</th>";
					else if (user.checkMissionLitige(m.getIdMission()).getStatutLitige().equals("enCours"))
						tabFinished = tabFinished + "<th>Litige en cours</th>";
				}
				tabFinished = tabFinished + "</tr>";

				finishedCount++;
			}
		}
		tabAvailable = tabAvailable + "</table>";
		tabWaiting = tabWaiting + "</table>";
		tabFinished = tabFinished + "</table>";

		if (availableCount == 0) {
			tabAvailable = "Aucune mission en attente";
		}
		if (waitingCount == 0) {
			tabWaiting = "Aucune mission en cours";
		}
		if (finishedCount == 0) {
			tabFinished = "Aucune mission terminée";
		}

		stringTab[0] = tabAvailable;
		stringTab[1] = tabWaiting;
		stringTab[2] = tabFinished;

		return stringTab;
	}

	public static void searchMission(PrintWriter out) {
		out.append("<html>"
				+ "<head>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>"
				+ "<link rel='stylesheet' href='src/main/webapp/WEB-INF/cssApp.css'>"
				+ "<title>Recherchez votre prochaine mission</title>"
				+ "</head>"
				+ "<body>"
				+ "<div class='container mt-3' id='mission' style='flex: 2; width: 150vh;'>"
				+ "<h2>Recherchez votre prochaine mission</h2>"
				+ "<form name='adressCleaner' method='POST' id='address' action='rechercheMission'>"
				+ "<div class='form-group'>"
				+ "<label for='street'>Rue :</label>"
				+ "<input type='text' class='form-control' name='street' id='street' required>"
				+ "</div>"
				+ "<div class='form-group'>"
				+ "<label for='city'>Ville :</label>"
				+ "<input type='text' class='form-control' name='city' id='city' required>"
				+ "</div>"
				+ "<div class='form-group'>"
				+ "<label for='postalcode'>Code Postal :</label>"
				+ "<input type='text' class='form-control' name='postalcode' id='postalcode' required>"
				+ "</div>"
				+ "<div class='form-group'>"
				+ "<label for='maxDistance'>Kilomètres maximum :</label>"
				+ "<input type='number' class='form-control' name='maxDistance' step='0.1' required>"
				+ "</div>"
				+ "<button type='submit' id='searchMission' class='btn btn-primary'>Rechercher</button>"
				+ "</form>"
				+ "</div>"
				+ "<script src='src/main/webapp/WEB-INF/jsApp.js'></script>"
				+ "</body>"
				+ "</html>");

	}

	public static String comboProprietaire(ArrayList<Property> tab) {
		String combo = "<select name=\"properties\" id=\"propties\">";

		for (Property p : tab) {
			combo = combo + "<option value=\"" + p.getIdProperty() + "\">" + p.getAdress() + "</option>";
		}

		combo = combo + "</select>";

		return combo;
	}

	public static void formMission(Proprietaire user, PrintWriter out) {
		out.append(
				"<form action=\"controllerproprietaire\" method=\"POST\" enctype=\"application/x-www-form-urlencoded\">");
		out.append("Propriétée<br>");

		out.append("<select name=\"properties\" id=\"propertieId\">");
		for (Property p : user.getProperties()) {
			out.append("<option value=\"" + p.getIdProperty() + "\">" + p.getAdress() + "</option>");
		}
		out.append("</select><br>");

		out.append("Instruction<br>");
		out.append("<input type=\"text\" name=\"instruction\" size=\"100\"/><br>");
		out.append("Date de la mission<br>");
		out.append(
				"<input type=\"date\" id=\"date\" name=\"mission_date\" value=\"2024-04-25\" min=\"2024-04-25\" max=\"2025-12-31\"><br>");
		out.append("Plage horraire de la mission<br>");
		out.append("<input type=\"text\" name=\"plageStart\" size=\"2\"/>");
		out.append("<input type=\"text\" name=\"plageEnd\" size=\"2\"/><br>");
		// out.append("<input type='range' min='8' max='20' value='50' class='slider'
		// id='missionTime'>");
		// out.append("<p>Valeur: <span id='value'></span></p>");
		// out.append("<script src='src/main/webapp/WEB-INF/jsApp.js'></script>");

		out.append("<br><input type=\"submit\" value=\"poster\">");
		out.append("</form>");
	}

	public static void vueMission(Mission m, PrintWriter out) {
		out.append("<h2>Ma mission</h2><hr>");
		out.append("Date de la mission :" + m.getDate());
		out.append("<br>Durée de la mission :" + m.getDuration());
		out.append("<br>Instruction de la mission :" + m.getInstruction());
		out.append("<br>Statut de la mission :" + m.getStatut());
		out.append("<br>Plage horraire de la mission :" + m.getLimit().get(0) + "-" + m.getLimit().get(1));
		System.out.println(m.getStatut());
		if (m.getStatut().equals("waiting") || m.getStatut().equals("finished")) {
			out.append("<br>Horraire du cleaner :" + m.getHoraireCleaner().get(0) + "-" + m.getHoraireCleaner().get(1));
		} else if (m.getStatut().equals("available")) {
			out.append(
					"<br><form action=\"annulermission\" method=\"post\"><input type=\"hidden\" name=\"idmission\" value=\""
							+ m.getIdMission() + "\"/><input type=\"submit\" value=\"Annuler la mission\"/></form>");
		}
	}

	public static void afficherImage(String path, PrintWriter out) {
		out.append("<img src=\"" + path + "\" alt=\"image\"/>");
	}

	public static void profilUser(PrintWriter out, Utilisateur user) {
		out.append("<div class='container mt-3' id='profilcontainer' style='flex: 1; border-right: 1px solid black;'>"
				+ "<h2>Profil de l'utilisateur</h2>"
				+ "<img src='./image/profil_picture/" + user.getUsername()
				+ ".jpg' alt='image_inch' class='img-thumbnail'>"
				+ "<br><br>"
				+ "<div class='form-group'>Nom : " + user.getFirstName() + " " + user.getSecondName() + "</div>"
				+ "<div class='form-group'>Email : " + user.getEmail() + "</div>"
				+ "<div class='form-group'>Pseudo : " + user.getUsername() + "</div>"
				+ "<div class='form-group'>Age : " + user.getAge() + "</div>"
				+ "<div class='form-group'>Note : " + user.getGlobalGrade() + "</div>"
				+ "<div class='form-group'>Téléphone : " + user.getPhoneNumber() + "</div>"
				+ "<button id='logout' class='btn btn-danger'>Logout</button>"
				+ "<br><br>"
				+ "<form name='modifprofil' action='ModifProfil'>"
				+ "<input type='submit' id='showProfil' value='Voir profil' class='btn btn-primary'>"
				+ "</form>"
				+ "</div>");
	}
}
