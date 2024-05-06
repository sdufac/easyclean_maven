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

		stringTab[0] = tabAvailable;
		stringTab[1] = tabWaiting;
		stringTab[2] = tabFinished;

		return stringTab;
	}

	public static void searchMission(PrintWriter out) {
		out.append("<head>");
		out.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.append("<link rel='stylesheet' href='src/main/webapp/WEB-INF/cssApp.css'>");
		out.append("</head>");
		out.append("<body>");
		out.append("<div id='mission' style='flex: 2;width: 150vh'>");
		out.append("<h3>Recherchez vôtre prochaine mission</h3>");
		out.append(
				"<form name='address' method='POST' id='address' style='width: 700px' action='/rechercheMission'>");
		out.append(
				"<div>rue :<input type='text' name='' id='city'></div>"
						+ "<div>ville :<input type='text' name='city' id='city'></div>"
						+ "<div>code postal :<input type='text' name='postalcode' id='postalcode'></div>");
		out.append("<div>");
		out.append("<p>Kilomètres maximum :</p>");
		out.append("<div class='slider-container'>");
		out.append("<input type='number' name='maxDistance'>");
		out.append("<div>");
		out.append("<button id='searchMission'>Rechercher</button>");
		out.append("</div>");
		out.append("<script src='src/main/webapp/WEB-INF/jsApp.js'></script>");
		out.append("</form>");
		out.append("</div>");
	}

	public static void profilUser(PrintWriter out, Utilisateur cleaner) {
		out.append("<div id='profilcontainer' style='flex: 1; border-right= 1;'>");
		out.append("<br>");
		out.append("<img src='localhost:9090/profil_picture/a.jpg' alt='image_inch'>");
		out.append("<br>");
		out.append("<div>Nom : ").append(cleaner.getFirstName() + " " + cleaner.getSecondName());
		out.append("</div>");
		out.append("<div>Email : ").append(cleaner.getEmail());
		out.append("</div>");
		out.append("<div>Pseudo : ").append(cleaner.getUsername());
		out.append("</div>");
		out.append("<div>Age : ").append(String.valueOf(cleaner.getAge()));
		out.append("</div>");
		out.append("<div> Note : ").append(String.valueOf(cleaner.getGlobalGrade()));
		out.append("</div>");
		out.append("<div>Téléphone : ").append(String.valueOf(cleaner.getPhoneNumber()));
		out.append("</div>");
		out.append("<button id='logout'>Logout</button>");
		out.append("<br>");
		out.append("<form name='modifprofil' action='modifProfil'>");
		out.append("<button id='showProfil' onAction='/modifProfil'> Voir profil</button>");
		out.append("</form>");
		out.append("</div>");
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

	public static String[] afficheMission(PrintWriter out, ArrayList<Mission> resultMission) {
		out.append("<h1>Résultats de la recherche de missions</h1>");
		out.append("<table border='1'>");
		out.append("<tr>");
		out.append("<th>Date</th>");
		out.append("<th>Adresse</th>");
		out.append("<th>Instruction</th>");
		out.append("<th>Instruction</th>");
		out.append("</tr>");
		for (Mission mission : resultMission) {
			out.append("<tr>");
			out.append("<td>").append(mission.getDate()).append("</td>");
			out.append("<td>").append(mission.getAdress()).append("</td>");
			out.append("<td>").append(mission.getInstruction()).append("</td>");
			out.append("</tr>");
		}
		out.append("</table>");

		return null;

	}
}
