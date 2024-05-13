package model;

import java.util.ArrayList;

/**
 * Classe administrateur
 * @author El Amine
 */

public class Admin extends Utilisateur{
	
	private ArrayList<Cleaner> cleaners;
	private ArrayList<Proprietaire> proprietaires;

	public Admin(String username, String firstName, String secondName, int age, String password, String email,
			String description, int phoneNumber, String dateOfBirth, float globalGrade) {
		super(username, firstName, secondName, age, password, email, phoneNumber, dateOfBirth, globalGrade,description);

		this.cleaners = new ArrayList<Cleaner>();
		this.proprietaires = new ArrayList<Proprietaire>();
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
	
	public void addCleaners (Cleaner c) {
		this.cleaners.add(c);
	}
	public void addProprietaires (Proprietaire p) {
		this.proprietaires.add(p);
	}

	public Cleaner getCleanerById(int id){
		for(Cleaner c:this.cleaners){
			if(c.getId() == id){
				return c;
			}
		}
		return null;
	}
	public Proprietaire getProprietaireById(int id){
		for(Proprietaire p:this.proprietaires){
			if(p.getId()==id){
				return p;
			}
		}
		return null;
	}
}
