package model;

import java.util.ArrayList;
import java.io.PrintWriter;

public abstract class HTMLfunction {

	public static String tabMission(ArrayList<Mission> mtab) {
		String html = "<table>";
		for (Mission m : mtab) {
			html = html + "<tr>";
			html = html + "<th>" + m.getAdress() + "</th>";
			html = html + "<th>" + m.getDate() + "</th>";
			html = html + "<th>" + m.getInstruction() + "</th>";
			html = html + "</tr>";
		}
		html = html + "</table>";

		return html;
	}

	public static String[] proprioTabMission(ArrayList<Mission> mtab) {
		String[] stringTab = new String[3];

		StringBuilder tabAvailable = new StringBuilder(
				"<table class='table table-bordered'><thead><tr><th>Adresse</th><th>Date</th><th>Instruction</th></tr></thead><tbody>");
		StringBuilder tabWaiting = new StringBuilder(
				"<table class='table table-bordered'><thead><tr><th>Adresse</th><th>Date</th><th>Instruction</th></tr></thead><tbody>");
		StringBuilder tabFinished = new StringBuilder(
				"<table class='table table-bordered'><thead><tr><th>Adresse</th><th>Date</th><th>Instruction</th></tr></thead><tbody>");

		int availableCount = 0;
		int waitingCount = 0;
		int finishedCount = 0;

		for (Mission m : mtab) {
			if (m.getStatut().equals("available")) {
				tabAvailable.append("<tr>")
						.append("<td>").append(m.getAdress()).append("</td>")
						.append("<td>").append(m.getDate()).append("</td>")
						.append("<td>").append(m.getInstruction()).append("</td>")
						.append("</tr>");
				availableCount++;
			} else if (m.getStatut().equals("waiting")) {
				tabWaiting.append("<tr>")
						.append("<td>").append(m.getAdress()).append("</td>")
						.append("<td>").append(m.getDate()).append("</td>")
						.append("<td>").append(m.getInstruction()).append("</td>")
						.append("</tr>");
				waitingCount++;
			} else if (m.getStatut().equals("finished")) {
				tabFinished.append("<tr>")
						.append("<td>").append(m.getAdress()).append("</td>")
						.append("<td>").append(m.getDate()).append("</td>")
						.append("<td>").append(m.getInstruction()).append("</td>")
						.append("</tr>");
				finishedCount++;
			}
		}
		tabAvailable.append("</tbody></table>");
		tabWaiting.append("</tbody></table>");
		tabFinished.append("</tbody></table>");

		if (availableCount == 0) {
			tabAvailable = new StringBuilder("Aucune mission en attente");
		}
		if (waitingCount == 0) {
			tabWaiting = new StringBuilder("Aucune mission en cours");
		}
		if (finishedCount == 0) {
			tabFinished = new StringBuilder("Aucune mission terminée");
		}

		stringTab[0] = tabAvailable.toString();
		stringTab[1] = tabWaiting.toString();
		stringTab[2] = tabFinished.toString();

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

	protected abstract Object getPhoneNumber();

	public static String comboProprietaire(ArrayList<Property> tab) {
		String combo = "<select name=\"properties\" id=\"propties\">";

		for (Property p : tab) {
			combo = combo + "<option value=\"" + p.getIdProperty() + "\">" + p.getAdress() + "</option>";
		}

		combo = combo + "</select>";

		return combo;
	}

	public static String[] modifProfil(PrintWriter out, Utilisateur user, ArrayList<Mission> mtab) {
		String[] tabString;
		tabString = new String[3];

		String tabAvailable = "<table>";
		String tabWaiting = "<table>";
		String tabFinished = "<table>";

		int availableCount = 0;
		int waitingCount = 0;
		int finishedCount = 0;

		for (Mission m : mtab) {
			if (m.getStatut().equals("available")) {
				tabAvailable = tabAvailable + "<tr>";
				tabAvailable = tabAvailable + "<th>" + m.getAdress() + "</th>";
				tabAvailable = tabAvailable + "<th>" + m.getDate() + "</th>";
				tabAvailable = tabAvailable + "<th>" + m.getInstruction() + "</th>";
				tabAvailable = tabAvailable + "</tr>";

				availableCount++;
			} else if (m.getStatut().equals("waiting")) {
				tabWaiting = tabWaiting + "<tr>";
				tabWaiting = tabWaiting + "<th>" + m.getAdress() + "</th>";
				tabWaiting = tabWaiting + "<th>" + m.getDate() + "</th>";
				tabWaiting = tabWaiting + "<th>" + m.getInstruction() + "</th>";
				tabWaiting = tabWaiting + "</tr>";

				waitingCount++;
			} else if (m.getStatut().equals("finished")) {
				tabFinished = tabFinished + "<tr>";
				tabFinished = tabFinished + "<th>" + m.getAdress() + "</th>";
				tabFinished = tabFinished + "<th>" + m.getDate() + "</th>";
				tabFinished = tabFinished + "<th>" + m.getInstruction() + "</th>";
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

		tabString[0] = tabAvailable;
		tabString[1] = tabWaiting;
		tabString[2] = tabFinished;

		return tabString;
	}

	public static ArrayList<Mission> afficheMission(PrintWriter out, ArrayList<Mission> resultMission) {
		out.print("<h1>Résultats de la recherche de missions</h1>");
		out.print("<table border='1'>");
		out.print("<tr>");
		out.print("<th>Date</th>");
		out.print("<th>Adresse</th>");
		out.print("<th>Instruction</th>");
		out.print("<th>Instruction</th>");
		out.print("</tr>");
		for (Mission mission : resultMission) {
			out.print("<tr>");
			out.print("<td>");
			out.print(mission.getDate());
			out.print("</td>");
			out.print("<td>");
			out.print(mission.getAdress());
			out.print("</td>");
			out.print("<td>");
			out.print(mission.getInstruction());
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");

		return resultMission;
	}

}
