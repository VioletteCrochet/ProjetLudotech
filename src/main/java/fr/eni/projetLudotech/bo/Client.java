package fr.eni.projetLudotech.bo;

import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Client {
	
	
	private Integer id;
	
	@Size(min = 3, max=50)
	@NotBlank
	private String nom;
	
	@Size(min = 3, max=50)
	@NotBlank
	private String prenom;
	
	@Size(min = 3, max=50)
	@NotBlank
	@Email 
	private String email;
	
	@Size(min = 3, max=50)
	private String numTel;
	
	@Size(min = 3, max=50)
	@NotBlank
	private String rue;
	
	@Size(min = 3, max=50)
	@NotBlank
	private String cpo;
	
	@Size(min = 3, max=50)
	@NotBlank
	private String ville;
	
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Integer id, String nom, String prenom, String email, String rue, String cpo, String ville) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.cpo = cpo;
		this.ville = ville;
	}

	public Client(Integer id, String nom, String prenom, String email, String numTel, String rue, String cpo,
			String ville) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numTel = numTel;
		this.rue = rue;
		this.cpo = cpo;
		this.ville = ville;
	}

	
	public String displayClient() {
		StringBuilder builder = new StringBuilder();
		builder.append("id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", email=");
		builder.append(email);
		builder.append(", numTel=");
		builder.append(numTel);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", cpo=");
		builder.append(cpo);
		builder.append(", ville=");
		builder.append(ville);
		return builder.toString();
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
		Client other = (Client) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", email=");
		builder.append(email);
		builder.append(", numTel=");
		builder.append(numTel);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", cpo=");
		builder.append(cpo);
		builder.append(", ville=");
		builder.append(ville);
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCpo() {
		return cpo;
	}
	public void setCpo(String cpo) {
		this.cpo = cpo;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
}
