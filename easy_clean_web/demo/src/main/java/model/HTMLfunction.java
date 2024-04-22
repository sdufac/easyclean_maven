package model;

import java.util.ArrayList;

public abstract class HTMLfunction {
	
	public static String tabMission(ArrayList <Mission>mtab) {
		String html = "<table>";
		for(Mission m : mtab) {
			html = html +"<tr>";
			html = html +"<th>"+m.getAdress()+"</th>";
			html = html +"<th>"+m.getDate()+"</th>";
			html = html +"<th>"+m.getInstruction()+"</th>";
			html = html +"</tr>";
		}
		html = html +"</table>";

		return html;
	}
}
