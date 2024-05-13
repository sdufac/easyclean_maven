package model;

public class Litige {
	private int id;
	private String textLitige;
	private Cleaner authorc;
	private Mission lmission;
	private Proprietaire authorp;
	private String image1;
	private String image2;
	private String image3;
	private String statutLitige;
	
	//Constructeur si l'auteur est un cleaner
    public Litige(Cleaner cleaner,String textLitige, String image1, String image2, String image3,Mission m,String statut) {
       this.authorc = cleaner;
       this.lmission = m;
       this.textLitige = textLitige;
       this.image1 = image1;
       this.image2 = image2;
       this.image3 = image3;
	   this.statutLitige = statut;
    }
    
	//Constructeur si l'auteur est un propriétaire
    public Litige(Proprietaire authorp, String textLitige, String image1, String image2, String image3,Mission lmission,String statut) {
        this.authorp= authorp;
    	this.textLitige = textLitige;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.lmission = lmission;
		this.statutLitige = statut;
    }
    
	public String getStatutLitige() {
		return statutLitige;
	}
	public void setStatutLitige(String statutLitige) {
		this.statutLitige = statutLitige;
	}
	public Mission getLmission() {
		return lmission;
	}
	public void setLmission(Mission lmission) {
		this.lmission = lmission;
	}
	public String getTextLitige() {
		return textLitige;
	}
	public void setTextLitige(String textLitige) {
		this.textLitige = textLitige;
	}
	public Cleaner getAuthorc() {
		return authorc;
	}
	public void setAuthorc(Cleaner authorc) {
		this.authorc = authorc;
	}
	public Proprietaire getAuthorp() {
		return authorp;
	}
	public void setAuthorp(Proprietaire authorp) {
		this.authorp = authorp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getText_litige() {
		return textLitige;
	}
	public void setText_litige(String text_litige) {
		this.textLitige = text_litige;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}

}
