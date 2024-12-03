package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Jeu;

public interface JeuService {
	public List<Jeu> findAllJeux();
	public Optional<Jeu> findById(Integer id);
	public void update(Jeu jeu);
	public void delete(Integer id);
	public void save(Jeu jeu);
	public Jeu add(Jeu jeu);
}
