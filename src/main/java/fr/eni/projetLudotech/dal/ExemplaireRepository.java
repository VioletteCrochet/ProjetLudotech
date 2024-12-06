package fr.eni.projetLudotech.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.ExemplaireJeu;
import fr.eni.projetLudotech.bo.Genre;

public interface ExemplaireRepository {
	
	public  void create(ExemplaireJeu exemplaire);
	public void update(ExemplaireJeu exemplaire);
	public List<ExemplaireJeu> findExemplaireByJeuid(Integer id);
	Optional<ExemplaireJeu> findExemplaireById(Integer id);
}
