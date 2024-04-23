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
	public static String[] proprioTabMission(ArrayList <Mission>mtab){
		String[] stringTab;
		stringTab = new String[3];
		
		String tabAvailable = "<table>";
		String tabWaiting = "<table>";
		String tabFinished = "<table>";

		int availableCount = 0;
		int waitingCount = 0;
		int finishedCount = 0;

		for(Mission m :mtab){
			if (m.getStatut().equals("available")) {
				tabAvailable = tabAvailable +"<tr>";
				tabAvailable = tabAvailable +"<th>"+m.getAdress()+"</th>";
				tabAvailable = tabAvailable +"<th>"+m.getDate()+"</th>";
				tabAvailable = tabAvailable +"<th>"+m.getInstruction()+"</th>";
				tabAvailable = tabAvailable +"</tr>";

				availableCount++;
			}
			else if(m.getStatut().equals("waiting")){
				tabWaiting = tabWaiting +"<tr>";
				tabWaiting = tabWaiting +"<th>"+m.getAdress()+"</th>";
				tabWaiting = tabWaiting +"<th>"+m.getDate()+"</th>";
				tabWaiting = tabWaiting +"<th>"+m.getInstruction()+"</th>";
				tabWaiting = tabWaiting +"</tr>";

				waitingCount++;
			}
			else if(m.getStatut().equals("finished")){
				tabFinished = tabFinished +"<tr>";
				tabFinished = tabFinished +"<th>"+m.getAdress()+"</th>";
				tabFinished = tabFinished +"<th>"+m.getDate()+"</th>";
				tabFinished = tabFinished +"<th>"+m.getInstruction()+"</th>";
				tabFinished = tabFinished +"</tr>";

				finishedCount++;
			}
		}
		tabAvailable = tabAvailable + "</table>";
		tabWaiting = tabWaiting + "</table>";
		tabFinished = tabFinished + "</table>";

		if(availableCount == 0){
			tabAvailable = "Aucune mission en attente";
		}
		if(waitingCount == 0){
			tabWaiting = "Aucune mission en cours";
		}
		if(finishedCount == 0){
			tabFinished = "Aucune mission terminée";
		}

		stringTab[0]=tabAvailable;
		stringTab[1]=tabWaiting;
		stringTab[2]=tabFinished;

		return stringTab;
	}
}
