package model;

import java.util.List;
import java.util.ArrayList;

public class Mission {
    private String date;
    private double duration;
    private List<Double> limit = new ArrayList<Double>();
    private List<Double> horaireCleaner = new ArrayList<Double>();
    private String instruction;
    private int idMission;
    private String adress;
    private int id_propriete;
    private int idProprio;
    private String statut;

    public Mission(String date, double duration, String instruction, double limitStart, double limitEnd, String adress, int id_Propriete, int idProprio,String statut) {
        this.date = date;
        this.duration = duration;
        this.instruction = instruction;
        this.limit.add(limitStart);
        this.limit.add(limitEnd);
        this.adress = adress;
        this.id_propriete = id_Propriete;
        this.idProprio = idProprio;   
        this.statut = statut;
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

	public int getId_propriete() {
		return id_propriete;
	}

	public void setId_propriete(int id_propriete) {
		this.id_propriete = id_propriete;
	}

	public int getId_proprio() {
        return id_propriete;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

	public int getIdProprio() {
		return idProprio;
	}

	public void setIdProprio(int idProprio) {
		this.idProprio = idProprio;
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
	

}
