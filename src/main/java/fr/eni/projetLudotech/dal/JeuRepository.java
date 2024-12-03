package fr.eni.projetLudotech.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Jeu;

public interface JeuRepository {
	void create(Jeu jeu);
	public List<Jeu> findAllJeux();
	public Optional<Jeu> findJeuById(Integer id);
	void update(Jeu jeu);
	public void delete(Integer id);
}
