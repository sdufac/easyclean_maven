package model;

import java.util.List;
import java.util.ArrayList;

public class Mission {
    private int idMission;
    private String date;
    private double duration;
    private List<Double> limit = new ArrayList<Double>();
    private List<Double> horaireCleaner = new ArrayList<Double>();
    private String instruction;
    private String statut;
    
    private Cleaner cleaner;
    private Property property;
    private Proprietaire proprietaire;

    public Mission(String date, double duration, String instruction, double limitStart, double limitEnd,Proprietaire p, Property property,String statut) {
        this.date = date;
        this.duration = duration;
        this.instruction = instruction;
        this.limit.add(limitStart);
        this.limit.add(limitEnd);
        this.statut = statut;
        this.property = property;
        this.proprietaire = p;
    }
    
    public void addHoraireCleaner(double a,double b) {
    	this.horaireCleaner.add(a);
    	this.horaireCleaner.add(b);
    }
    public List<Double> getHoraireCleaner(){
    	return this.horaireCleaner;
    }

    public static double CalculDureeMission(int surface) {
        double durationMission = 0;

        durationMission = (surface / 50) * 0.5;

        return durationMission;
    }
    
    
    public List<Double> getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit.add(limit);
	}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getIdMission() {
        return idMission;
    }
    
    public void setIdMission(int id) {
    	this.idMission = id;
    }

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

    public void setHoraireCleaner(double horaire1,double horaire2){
        this.horaireCleaner.add(horaire1);
        this.horaireCleaner.add(horaire2);
    }
	

    // public void calculDureeMission(int surface) {
    // double durationMission = (surface / 50.0) * 0.5;
    // this.duration = String.valueOf(durationMission);
    // }

    public void setCleaner(Cleaner c){
        this.cleaner = c;
    }
    public Cleaner getCleaner(){
        return this.cleaner;
    }

    public void setProprietaire(Proprietaire p){
        this.proprietaire = p;
    }
    public Proprietaire getProprietaire(){
        return this.proprietaire;
    }

    public void setProperty(Property p){
        this.property = p;
    }
    public Property getProperty(){
        return this.property;
    }
}
