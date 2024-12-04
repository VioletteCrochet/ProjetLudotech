package fr.eni.projetLudotech.bo;

import java.util.Objects;

public class ExemplaireJeu {
	private Integer id;
	private String codeBarre;
	private boolean louable;
	private Jeu jeu;

	public ExemplaireJeu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExemplaireJeu(String codeBarre, boolean louable, Jeu jeu) {
		super();
		this.codeBarre = codeBarre;
		this.louable = louable;
		this.jeu = jeu;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExemplaireJeu [id=");
		builder.append(id);
		builder.append(", codeBarre=");
		builder.append(codeBarre);
		builder.append(", louable=");
		builder.append(louable);
		builder.append(", jeu=");
		builder.append(jeu);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeBarre, id, jeu, louable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExemplaireJeu other = (ExemplaireJeu) obj;
		return Objects.equals(codeBarre, other.codeBarre) && Objects.equals(id, other.id)
				&& Objects.equals(jeu, other.jeu) && louable == other.louable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public boolean isLouable() {
		return louable;
	}

	public void setLouable(boolean louable) {
		this.louable = louable;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

}
