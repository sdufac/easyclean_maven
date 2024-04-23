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

	public static void searchMission(PrintWriter out) {
		out.append("<!DOCTYPE html>");
		out.append("<html lang='fr'>");
		out.append("<head>");
		out.append("<meta charset='UTF-8'>");
		out.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.append("<title>Slider Simple</title>");
		out.append("<link rel='stylesheet' href='src/main/webapp/WEB-INF/cssApp.css'>");
		out.append("</head>");
		out.append("<body>");
		out.append("<form name='result' id='result_search'>");
		out.append("<div class='slider-container'>");
		out.append("<input type='range' min='1' max='100' value='50' class='slider' id='myRange'>");
		out.append("<p>Valeur: <span id='value'></span></p>");
		out.append("</div>");
		out.append("<script src='src/main/webapp/WEB-INF/jsApp.js'></script>");
		out.append("</form>");
		out.append("</body>");
		out.append("</html>");
	}
}
