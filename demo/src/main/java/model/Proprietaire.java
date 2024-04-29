package model;

import java.util.ArrayList;

/**
 * Classe de demandeur de menage, implementer un vector de Propriete quand la classe sera faites
 * @author Simon
 */
public class Proprietaire extends Utilisateur{

	ArrayList<Mission> missions;
	ArrayList<Property> properties;
	ArrayList<Mission> postulations;
	String statut;

	public Proprietaire(String firstName, String secondName,String username,String email, String password, 
			 int age,String description, int phoneNumber, String dateOfBirth,float globalGrade) {
		super(username, firstName, secondName, age, password, email, phoneNumber, dateOfBirth,globalGrade,description);
		this.statut = "Proprietaire";
		this.properties = new ArrayList<Property>();
		this.missions = new ArrayList<Mission>();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Mission> getMissions(){
		return missions;
	}
	
	public void setMission(Mission mission) {
		this.missions.add(mission);
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(Property properties) {
		this.properties.add(properties);
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public ArrayList<Mission> getPostulation(){
		return this.postulations;
	}
	
	public void setPostulation(Mission m){
		this.postulations.add(m);
	}
	
}
