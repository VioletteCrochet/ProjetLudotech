package fr.eni.projetLudotech.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Genre {
	private int id;
	private String libelle;
	
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Genre( String libelle) {
		super();
		this.libelle = libelle;
	}

	public Genre(int id, String libelle, List<Jeu> jeux) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Genre [id=");
		builder.append(id);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
