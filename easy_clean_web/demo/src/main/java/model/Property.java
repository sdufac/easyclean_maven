package model;

public class Property {
	private int idProperty;
    private String adress;
    private int num_adress;
    private int surface;

    public Property(int id,String adress, int num_adress, int surface) {
        this.idProperty = id;
    	this.adress = adress;
        this.num_adress = num_adress;
        this.surface = surface;
    }
    
    
    public int getIdProperty() {
		return idProperty;
	}


	public void setIdProperty(int idProperty) {
		this.idProperty = idProperty;
	}


	public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getNum_adress() {
        return num_adress;
    }

    public void setNum_adress(int num_adress) {
        this.num_adress = num_adress;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }


	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}
