package fr.eni.projetLudotech.bo;

import java.util.Objects;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExemplaireJeu {
	private Integer id;
	@NotNull
	@Size(min=13, max=13)
	private String codeBarre;
	@NotNull
	private boolean louable;
	@NotNull
	private Integer jeuId;

	public ExemplaireJeu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExemplaireJeu(Integer id, String codeBarre, boolean louable, Integer jeuId) {
		super();
		this.id = id;
		this.codeBarre = codeBarre;
		this.louable = louable;
		this.jeuId = jeuId;
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
		builder.append(jeuId);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeBarre, id, jeuId, louable);
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
				&& Objects.equals(jeuId, other.jeuId) && louable == other.louable;
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

	public Integer getJeuId() {
		return jeuId;
	}

	public void setJeuId(Integer jeuId) {
		this.jeuId = jeuId;
	}

}
