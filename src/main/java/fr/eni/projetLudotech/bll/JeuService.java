package fr.eni.projetLudotech.bll;

import java.util.Optional;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Jeu;

public interface JeuService {
	public void delete();
	public Optional<Jeu> findById(Integer id);
	public void update(Jeu jeu);
	public void delete(Integer id);
	public void save(Jeu jeu);
	public Client add(Jeu jeu);
}
