package model;

public class Postulation {
    private int id;
    private Mission mission;
    private Cleaner cleaner;
    private float horaireStart;
    private float horaireEnd;
    private float salaireCleaner;

    public Postulation(int id, Mission mission, Cleaner cleaner, float horaireStart, float horaireEnd, float salaireCleaner){
        this.id = id;
        this.mission = mission;
        this.cleaner = cleaner;
        this.horaireStart = horaireStart;
        this.horaireEnd = horaireEnd;
        this.salaireCleaner = salaireCleaner;
    }

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }

    public void setMission(Mission m){
        this.mission = m;
    }
    public Mission getMission(){
        return this.mission;
    }

    public void setCleaner(Cleaner cleaner){
        this.cleaner = cleaner;
    }
    public Cleaner getCleaner(){
        return this.cleaner;
    }

    public void setHoraireStart(float start){
        this.horaireStart = start;
    }
    public float getHoraireStart(){
        return this.horaireStart;
    }

    public void setHoraireEnd(float end){
        this.horaireEnd = end;
    }
    public float getHoraireEnd(){
        return this.horaireEnd;
    }

    public void setSalaireCleaner(float salaire){
        this.salaireCleaner = salaire;
    }
    public float getSalaireCleaner(){
        return this.salaireCleaner;
    }
}




