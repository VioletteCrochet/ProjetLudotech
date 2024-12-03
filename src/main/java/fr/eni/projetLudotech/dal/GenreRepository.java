package fr.eni.projetLudotech.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Genre;

public interface GenreRepository {

    void create(Genre genre);

    List<Genre> findAllGenres();

    Optional<Genre> findGenreById(Integer id);

    void update(Genre genre);

    void delete(Integer id);

    List<Genre> findGenresByJeuId(Integer jeuId);
}
