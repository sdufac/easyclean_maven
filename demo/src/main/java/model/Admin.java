package model;

import java.util.ArrayList;

/**
 * Classe administrateur
 * @author El Amine
 */

public class Admin extends Utilisateur{
	
	private ArrayList<Mission> missions;
	private ArrayList<Cleaner> cleaners;
	private ArrayList<Proprietaire> proprietaires;
	private ArrayList<Litige> litiges;

	public Admin(String username, String firstName, String secondName, int age, String password, String email,
			String description, int phoneNumber, String dateOfBirth, float globalGrade) {
		super(username, firstName, secondName, age, password, email, phoneNumber, dateOfBirth, globalGrade,description);
		
		this.missions = new ArrayList<Mission>();
		this.cleaners = new ArrayList<Cleaner>();
		this.proprietaires = new ArrayList<Proprietaire>();
		this.litiges = new ArrayList<Litige>();
	}

	public ArrayList<Mission> getMissions() {
		return missions;
	}

	public void setMissions(ArrayList<Mission> missions) {
		this.missions = missions;
	}

	public ArrayList<Cleaner> getCleaners() {
		return cleaners;
	}

	public void setCleaners(ArrayList<Cleaner> cleaners) {
		this.cleaners = cleaners;
	}

	public ArrayList<Proprietaire> getProprietaires() {
		return proprietaires;
	}

	public void setProprietaires(ArrayList<Proprietaire> proprietaires) {
		this.proprietaires = proprietaires;
	}
	
	public ArrayList<Litige> getLitiges() {
		return litiges;
	}

	public void setLitiges(ArrayList<Litige> litiges) {
		this.litiges = litiges;
	}
	
	
	public void addMissions (Mission m) {
		this.missions.add(m);
	}
	public void addCleaners (Cleaner c) {
		this.cleaners.add(c);
	}
	public void addProprietaires (Proprietaire p) {
		this.proprietaires.add(p);
	}
	public void addLitiges (Litige l) {
		this.litiges.add(l);
	}
}
