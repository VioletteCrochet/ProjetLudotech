package fr.eni.projetLudotech.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projetLudotech.bo.Genre;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public GenreRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void create(Genre genre) {
		String sql = "insert into Genres (libelle) values (:libelle)";
		Map<String, Object> params = new HashMap<>();
		params.put("libelle", genre.getLibelle());

		int nbRows = namedParameterJdbcTemplate.update(sql, params);
		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été ajoutée pour le genre: " + genre);
		}
	}

	@Override
	public List<Genre> findAllGenres() {
		String sql = "select id, libelle from Genres";
		return namedParameterJdbcTemplate.query(sql, new GenreRowMapper());
	}

	@Override
	public Optional<Genre> findGenreById(Integer id) {
		String sql = "select id, libelle from Genres where id = :id";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		Genre genre = namedParameterJdbcTemplate.queryForObject(sql, params, new GenreRowMapper());
		return Optional.ofNullable(genre);

	}

	@Override
	public void update(Genre genre) {
		String sql = "update Genres set libelle = :libelle where id = :id";
		Map<String, Object> params = new HashMap<>();
		params.put("libelle", genre.getLibelle());
		params.put("id", genre.getId());

		int nbRows = namedParameterJdbcTemplate.update(sql, params);
		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été mise à jour pour le genre avec l'id: " + genre.getId());
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from Genres where id = :id";
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		int nbRows = namedParameterJdbcTemplate.update(sql, params);
		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été supprimée pour le genre avec l'id : " + id);
		}
	}

	@Override
	public List<Genre> findGenresByJeuId(Integer jeuId) {
		String sql = "select g.id, g.libelle from Genres g " + "join Jeux_Genres jg on g.id = jg.genre_id "
				+ "where jg.jeu_id = :jeuId";
		Map<String, Object> params = new HashMap<>();
		params.put("jeuId", jeuId);

		return namedParameterJdbcTemplate.query(sql, params, new GenreRowMapper());
	}

	// Classe interne GenreRowMapper pour mapper les résultats de la base de données
	// à des objets Genre
	private static class GenreRowMapper implements RowMapper<Genre> {
		@Override
		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
			Genre genre = new Genre();
			genre.setId(rs.getInt("id"));
			genre.setLibelle(rs.getString("libelle"));
			return genre;
		}
	}
}
