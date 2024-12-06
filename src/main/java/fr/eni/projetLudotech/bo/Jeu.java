package fr.eni.projetLudotech.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Jeu {
	
	private Integer id;
	@NotBlank
	private String titre;
	@NotBlank
	private String reference;
	private String description;
	@NotNull
	@Min(0)
	private float tarifJour;
	@NotNull
	@Min(0)
	private Integer ageMin;
	
	private Integer duree;
	@NotEmpty
	private List<Genre> Genres = new ArrayList<>();
	
	private List<ExemplaireJeu> exemplaires = new ArrayList<>();
	
	
	public Jeu() {
		super();
	}
	
	public Jeu(Integer id, String titre, String reference, float tarifJour, Integer ageMin) {
		super();
		this.id = id;
		this.titre = titre;
		this.reference = reference;
		this.tarifJour = tarifJour;
		this.ageMin = ageMin;
	}
	
	
	public Jeu(Integer id, String titre, String reference, String description, float tarifJour, Integer ageMin, Integer duree) {
		super();
		this.id = id;
		this.titre = titre;
		this.reference = reference;
		this.description = description;
		this.tarifJour = tarifJour;
		this.ageMin = ageMin;
		this.duree = duree;
	}

	

	public Jeu(Integer id, String titre, String reference, String description, float tarifJour, Integer ageMin, Integer duree,
			List<Genre> genres) {
		super();
		this.id = id;
		this.titre = titre;
		this.reference = reference;
		this.description = description;
		this.tarifJour = tarifJour;
		this.ageMin = ageMin;
		this.duree = duree;
		this.Genres = genres;
	}

	public Jeu(@NotBlank Integer id, @NotBlank String titre, @NotBlank String reference, String description,
			@NotBlank float tarifJour, @NotBlank Integer ageMin, Integer duree, @NotBlank List<Genre> genres,
			List<ExemplaireJeu> exemplaires) {
		super();
		this.id = id;
		this.titre = titre;
		this.reference = reference;
		this.description = description;
		this.tarifJour = tarifJour;
		this.ageMin = ageMin;
		this.duree = duree;
		this.Genres = genres;
		this.exemplaires = exemplaires;
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
		Jeu other = (Jeu) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Jeu [id=");
		builder.append(id);
		builder.append(", titre=");
		builder.append(titre);
		builder.append(", reference=");
		builder.append(reference);
		builder.append(", description=");
		builder.append(description);
		builder.append(", tarifJour=");
		builder.append(tarifJour);
		builder.append(", ageMin=");
		builder.append(ageMin);
		builder.append(", duree=");
		builder.append(duree);
		builder.append(", Genres=");
		builder.append(Genres);
		builder.append("]");
		return builder.toString();
	}
	

	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getAgeMin() {
		return ageMin;
	}
	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}
	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public List<Genre> getGenres() {
		return Genres;
	}

	public void setGenres(List<Genre> genres) {
		Genres = genres;
	}

	public Integer getDuree() {
		return duree;
	}

	public List<ExemplaireJeu> getExemplaires() {
		return exemplaires;
	}

	public void setExemplaires(List<ExemplaireJeu> exemplaires) {
		this.exemplaires = exemplaires;
	}

	

	
}
