package fr.eni.projetLudotech.bo;

import java.time.LocalDate;
import java.util.Objects;

public class DetailLocation {
	private Integer numLigne;
	private LocalDate dateRetour;
	private float TarifLocation;
	private Location location;
	private ExemplaireJeu exemplaireJeu;
	
	
	public DetailLocation(Integer numLigne, LocalDate dateRetour, float tarifLocation, Location location, ExemplaireJeu exemplaireJeu) {
		super();
		this.numLigne = numLigne;
		this.dateRetour = dateRetour;
		TarifLocation = tarifLocation;
		this.location = location;
		this.exemplaireJeu = exemplaireJeu;
	}

	public DetailLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DetailLocation(Integer numLigne, float tarifLocation, Location location, ExemplaireJeu exemplaireJeu) {
		super();
		this.numLigne = numLigne;
		TarifLocation = tarifLocation;
		this.location = location;
		this.exemplaireJeu = exemplaireJeu;
	}

	public Integer getNumLigne() {
		return numLigne;
	}
	public void setNumLigne(Integer numLigne) {
		this.numLigne = numLigne;
	}
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	public float getTarifLocation() {
		return TarifLocation;
	}
	public void setTarifLocation(float tarifLocation) {
		TarifLocation = tarifLocation;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public ExemplaireJeu getExemplaireJeu() {
		return exemplaireJeu;
	}
	public void setExemplaireJeu(ExemplaireJeu exemplaireJeu) {
		this.exemplaireJeu = exemplaireJeu;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DetailLocation [numLigne=");
		builder.append(numLigne);
		builder.append(", dateRetour=");
		builder.append(dateRetour);
		builder.append(", TarifLocation=");
		builder.append(TarifLocation);
		builder.append(", location=");
		builder.append(location);
		builder.append(", jeux=");
		builder.append(exemplaireJeu);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		return Objects.hash(location, numLigne);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailLocation other = (DetailLocation) obj;
		return Objects.equals(location, other.location) && Objects.equals(numLigne, other.numLigne);
	}

}
