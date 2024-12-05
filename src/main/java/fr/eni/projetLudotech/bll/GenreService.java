package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Genre;
import fr.eni.projetLudotech.exceptions.ClientNotFoundException;

public interface GenreService {
	public List<Genre> findAllGenres();
	public Optional<Genre> findGenreById(int id);
	public void update(Genre genre);
	public void delete(int id);
	public void save(Genre genre);
	public void add(Genre genre);
}
