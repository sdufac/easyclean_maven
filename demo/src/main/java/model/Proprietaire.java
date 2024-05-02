package model;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Classe de demandeur de menage, implementer un vector de Propriete quand la classe sera faites
 * @author Simon
 */
public class Proprietaire extends Utilisateur{

	private ArrayList<Mission> missions;
	private ArrayList<Property> properties;
	private ArrayList<Postulation> postulations;
	private String statut;

	public Proprietaire(String firstName, String secondName,String username,String email, String password, 
			 int age,String description, int phoneNumber, String dateOfBirth,float globalGrade) {
		super(username, firstName, secondName, age, password, email, phoneNumber, dateOfBirth,globalGrade,description);
		this.statut = "Proprietaire";
		this.properties = new ArrayList<Property>();
		this.missions = new ArrayList<Mission>();
		this.postulations = new ArrayList<Postulation>();
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

	public ArrayList<Postulation> getPostulation(){
		return this.postulations;
	}
	public Postulation getPostulationById(int idPostulation){
		for(Postulation p:this.getPostulation()){
			if(p.getId()==idPostulation){
				return p;
			}
		}
		return null;
	}
	public void setPostulation(Postulation p){
		this.postulations.add(p);
	}
	public void setPostulation(ArrayList<Postulation> ptab){
		this.postulations = ptab;
	}

	
	public void removePostulation(int idMission){
		for(Postulation p :this.getPostulation()){
			if(p.getMission().getIdMission() == idMission){
				this.getPostulation().remove(p);
			}
		}
	}

	public Mission getMissionById(int idMission){
		for(Mission m: this.getMissions()){
			if(m.getIdMission() == idMission){
				return m;
			}
		}
		return null;
	}
}
