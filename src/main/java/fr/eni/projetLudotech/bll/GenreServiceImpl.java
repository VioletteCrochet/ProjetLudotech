package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.projetLudotech.bo.Genre;
import fr.eni.projetLudotech.dal.GenreRepository;

@Service("genreService")
public class GenreServiceImpl implements GenreService{

	private GenreRepository genreRepo;
	
	public GenreServiceImpl( GenreRepository genreRepo) {
		this.genreRepo = genreRepo;
	}
	
	@Override
	public List<Genre> findAllGenres() {
		return genreRepo.findAllGenres();
	}

	@Override
	public Optional<Genre> findGenreById(int id) {
		return genreRepo.findGenreById(id);
	}

	@Override
	public void update(Genre entity) {
		genreRepo.update(entity);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Genre genre) {
		genreRepo.create(genre);
		
	}
	
}
