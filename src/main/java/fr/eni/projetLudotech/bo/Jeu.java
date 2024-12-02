package fr.eni.projetLudotech.bo;

public class Jeu {
	private int idJeu;
	private String titre;
	private String reference;
	private String description;
	private float tarifJour;
	private int ageMin;
	private int Duree;
	
	public Jeu() {
		super();
	}
	
	public Jeu(int idJeu, String titre, String reference, float tarifJour, int ageMin) {
		super();
		this.idJeu = idJeu;
		this.titre = titre;
		this.reference = reference;
		this.tarifJour = tarifJour;
		this.ageMin = ageMin;
	}
	
	
	public Jeu(int idJeu, String titre, String reference, String description, float tarifJour, int ageMin, int duree) {
		super();
		this.idJeu = idJeu;
		this.titre = titre;
		this.reference = reference;
		this.description = description;
		this.tarifJour = tarifJour;
		this.ageMin = ageMin;
		Duree = duree;
	}

	//getters and setters
	public int getIdJeu() {
		return idJeu;
	}
	public void setIdJeu(int idJeu) {
		this.idJeu = idJeu;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getTarifJour() {
		return tarifJour;
	}
	public void setTarifJour(float tarifJour) {
		this.tarifJour = tarifJour;
	}
	public int getAgeMin() {
		return ageMin;
	}
	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}
	public int getDuree() {
		return Duree;
	}
	public void setDuree(int duree) {
		Duree = duree;
	}
	
}
