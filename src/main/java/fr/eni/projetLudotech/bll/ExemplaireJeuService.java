package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.ExemplaireJeu;

public interface ExemplaireJeuService {
	public void add(ExemplaireJeu exemplaireJeu, Integer JeuId);
	public List<ExemplaireJeu> findExemplaireByJeuId(Integer JeuId);
	public Optional<ExemplaireJeu> findExemplaireById(Integer id);
	
}
