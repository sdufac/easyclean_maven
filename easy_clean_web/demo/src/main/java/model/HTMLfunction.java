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
		out.append("<form name='result' id='result_search' style='width: 500px' action='/rechercheMission'>");
		out.append("<h3>Recherchez vôtre prochaine mission</h3>");
		out.append("<div>Adresse : <div><input type='text' name='address' id='address'></div></div>");
		out.append("<p>Kilomètres maximum :</p>");
		out.append("<div>");
		out.append("<div class='slider-container'>");
		out.append("<input type='range' min='0' max='100' value='0' class='slider' id='myRange'>");
		out.append("</div>");
		out.append("<div class='slider-labels'>");
		out.append("<span class='slider-value'>0</span>");
		out.append("<span class='slider-value'>5</span>");
		out.append("<span class='slider-value'>10</span>");
		out.append("<span class='slider-value'>15</span>");
		out.append("<span class='slider-value'>20</span>");
		out.append("<span class='slider-value'>30</span>");
		out.append("<span class='slider-value'>50</span>");
		out.append("<span class='slider-value'>75</span>");
		out.append("<span class='slider-value'>100</span>");
		out.append("</div>");
		out.append("</div>");
		out.append("<div>");
		out.append("<button id='searchMission'>Rechercher</button>");
		out.append("</div>");
		out.append("<script src='src/main/webapp/WEB-INF/jsApp.js'></script>");
		out.append("</form>");
		out.append("</div>");
	}

	public static void profilUser(PrintWriter out) {
		out.append("<div id='profilcontainer' style='flex: 1; border-right= 1;>");
		out.append("<img src='src/main/webapp/WEB-INF/profil_picture/'>");
		out.append("<br>");
		out.append("<button id='logout'>Logout</button>");
		out.append("<br>");
		out.append("<button id='showProfil'>Voir profil</button>");
		out.append("</div>");
	}

	public static String comboProprietaire(ArrayList<Property> tab){
		String combo = "<select name=\"properties\" id=\"propties\">";

		for(Property p: tab){
			combo = combo+"<option value=\""+ p.getIdProperty() +"\">"+p.getAdress()+"</option>";
		}

		combo = combo + "</select>";

		return combo;
	}
}
