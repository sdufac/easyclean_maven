package model;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;
/**
 * Class mere utilisateur regroupant touts les attributs et methodes communes aux trois types d'utilisateurs.
 * @author Simon 
 */
public class Utilisateur {
	
	private int id;
	private String username;
	private String firstName;
	private String secondName;
	private int age;
	private String password;
	private String profilePicture;
	private String email;
	private String description;
	private int phoneNumber;
	private String dateOfBirth;
	private Date dateOfCreation;
	private HashMap<Float, String> comment;
	float globalGrade;

	public Utilisateur(String username, String firstName, String secondName, int age, String password,
			String email, int phoneNumber, String dateOfBirth, float globalGrade,String description) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.secondName = secondName;
		this.age = age;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.comment = new HashMap<Float, String>();
		this.globalGrade = globalGrade;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getGlobalGrade() {
		return globalGrade;
	}

	public void setGlobalGrade(float globalGrade) {
		this.globalGrade = globalGrade;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	/**
	 * String indiquant le nom de l'image pour aller la chercher dans la bdd
	 * @return
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * Date au format jour/mois/annee
	 * @return
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public HashMap<Float, String> getComment() {
		return comment;
	}
	/**
	 * Hashmap regroupant les commentaires attribue a l'utilisateur avec la note en float et le commentaire en String
	 * @param a Note en decimal
	 * @param b Commentaire
	 */
	public void setComment(float a, String b) {
		this.comment.put(a, b);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Utilisateur [username=" + username + ", password=" + password + ", profilePicture=" + profilePicture
				+ ", email=" + email + ", description=" + description + ", phoneNumber=" + phoneNumber
				+ ", dateOfBirth=" + dateOfBirth + ", dateOfCreation=" + dateOfCreation + ", getUsername()="
				+ getUsername() + ", getPassword()=" + getPassword() + ", getProfilePicture()=" + getProfilePicture()
				+ ", getEmail()=" + getEmail() + ", getDescription()=" + getDescription() + ", getPhoneNumber()="
				+ getPhoneNumber() + ", getDateOfBirth()=" + getDateOfBirth() + ", getDateOfCreation()="
				+ getDateOfCreation() + "]";
	}
	public void comment(Utilisateur a,float note, String comment) {
		a.setComment(note,comment);
	}
	public void printComment() {
		System.out.println(this.comment);
	}
	public void getMoy() {
		float moy = 0;
		for(Map.Entry<Float, String> entry :  this.comment.entrySet()) {
			moy = moy + entry.getKey();
		}
		moy = moy/this.comment.size();
		this.globalGrade = moy;	
	}
}