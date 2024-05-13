package model;

import java.util.ArrayList;

public class Cleaner extends Utilisateur{
	private int missionDone;
	private int salaryPerHour;
	private String statut;
	private int perimeter;
	ArrayList<Postulation> postulation;

	public Cleaner(String firstName, String secondName, String username, String email, String password,
			int age, String description, int phoneNumber, String dateOfBirth, float globalGrade, int missionDone,
			int perimeter, int salaryPerHour) {
		super(username, firstName, secondName, age, password, email, phoneNumber, dateOfBirth, globalGrade,
				description);
		this.statut = "Cleaner";
		this.missionDone = missionDone;
		this.salaryPerHour = salaryPerHour;
		this.perimeter = perimeter;
		this.postulation = new ArrayList<Postulation>();
	}
	
	
	
	public ArrayList<Postulation> getPostulation() {
		return postulation;
	}

	public void addPostulation(Postulation p) {
		this.postulation.add(p);
	}



	public double calculSalary(double tempsMission) {
		double salary;
		
		salary = this.salaryPerHour * tempsMission;
		
		return salary;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getMissionDone() {
		return missionDone;
	}

	public void setMissionDone(int missionDone) {
		this.missionDone = missionDone;
	}

	public int getPerimeter() {
		return this.perimeter;
	}

	public void setPerimeter(int perimeter) {
		this.perimeter = perimeter;
	}

	public int getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(int salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	public void setId_user(int i) {
		// TODO Auto-generated method stub
		
	}
}
