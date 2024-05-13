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
		out.print("<head>");
		out.print("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.print("<link rel='stylesheet' href='src/main/webapp/WEB-INF/cssApp.css'>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div id='mission' style='flex: 2;width: 150vh'>");
		out.print("<h3>Recherchez vôtre prochaine mission</h3>");
		out.print(
				"<form name='adressCleaner' method='POST' id='address' action='rechercheMission'>"
						+ "<div>Rue :<input type='text' name='street' id='street'></div>"
						+ "<div>Ville :<input type='text' name='city' id='city'></div>"
						+ "<div>Code Postal :<input type='text' name='postalcode' id='postalcode'></div>"
						+ "<div>"
						+ "<p>Kilomètres maximum :</p>"
						+ "<input type='number' name='maxDistance' step='0.1' required>"
						+ "</div>"
						+ "<div>"
						+ "<input type='submit' id='searchMission' value='Rechercher'>"
						+ "</div>"
						+ "</form>");
		out.print("<script src='src/main/webapp/WEB-INF/jsApp.js'></script>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>"
				+ "</html>");

	}

	public static void profilUser(PrintWriter out, Utilisateur user) {
		out.print("<head>");
		out.print("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.print("<link rel='stylesheet' href='src/main/webapp/WEB-INF/cssApp.css'>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div id='profilcontainer' style='flex: 1; border-right= 1;'>");
		out.print("<br>");
		out.print("<img src=./image/profil_picture/" + user.getUsername() + ".jpg' alt='image_inch'>");
		out.print("<br>");
		out.print("<div>Nom : ");
		out.print(user.getFirstName() + " " + user.getSecondName());
		out.print("</div>");
		out.print("<div>Email : ");
		out.print(user.getEmail());
		out.print("</div>");
		out.print("<div>Pseudo : ");
		out.print(user.getUsername());
		out.print("</div>");
		out.print("<div>Age : ");
		out.print(String.valueOf(user.getAge()));
		out.print("</div>");
		out.print("<div> Note : ");
		out.print(String.valueOf(user.getGlobalGrade()));
		out.print("</div>");
		out.print("<div>Téléphone : ");
		out.print(String.valueOf(user.getPhoneNumber()));
		out.print("</div>");
		out.print("<button id='logout'>Logout</button>");
		out.print("<br>");
		out.print("<form name='modifprofil' action='ModifProfil'>");
		out.print("<input type='submit' id='showProfil' value='voir profil'>");
		out.print("</form>");
		out.print("</div>");
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
