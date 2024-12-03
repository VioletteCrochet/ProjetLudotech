package fr.eni.projetLudotech.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Genre;
import fr.eni.projetLudotech.bo.Jeu;

@Repository
public class JeuRepositoryImpl implements JeuRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public JeuRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void create(Jeu jeu) {

		String sql = "insert into Jeux (titre, reference, description, tarifJour, ageMin, duree) "
				+ "values (:titre, :reference, :description, :tarifjour, :ageMin, :duree)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu), keyHolder,
				new String[] { "id" });
		jeu.setId(keyHolder.getKeyAs(Integer.class));

		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été ajoutée pour le jeu: " + jeu);
		}
	}

	@Override
	public List<Jeu> findAllJeux() {
		String sql = "select id, titre, reference, description, tarifJour, ageMin, duree from Jeux";
		List<Jeu> jeux = namedParameterJdbcTemplate.query(sql, new JeuRowMapper());
		return jeux;
	}

	@Override
	public Optional<Jeu> findJeuById(Integer id) {
		String sql = "select id, titre, reference, description, tarifJour, ageMin, duree from Jeux where id = :id";

		Jeu jeu = jdbcTemplate.queryForObject(sql, new JeuRowMapper(), id);
		return Optional.ofNullable(jeu);

	}

	@Override
	public void update(Jeu jeu) {
		String sql = "update Jeux set titre = :titre, reference = :reference, description = :description, tarifJour = :tarifJour, ageMin = :ageMin, duree = :duree where id = :id";
		
		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu));
		
		if (nbRows != 1) {
	        throw new RuntimeException("Aucune ligne n'a été mise à jour pour le client avec l'id: " + jeu.getId());
	    }
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from Jeux where id = :id";
		
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		
		int nbRows = namedParameterJdbcTemplate.update(sql, params);

		
		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été supprimée pour le client avec l'id : " + id);
		}

	}

	class JeuRowMapper implements RowMapper<Jeu> {

		@Override
		public Jeu mapRow(ResultSet rs, int rowNum) throws SQLException {
			Jeu jeu = new Jeu();
			jeu.setId(rs.getInt("id"));
			jeu.setTitre(rs.getString("titre"));
			jeu.setReference(rs.getString("reference"));
			jeu.setDescription(rs.getString("description"));
			jeu.setTarifJour(rs.getInt("tarifJour"));
			jeu.setAgeMin(rs.getInt("ageMin"));
			jeu.setDuree(rs.getInt("duree"));
			// Gestion des genres : cette partie suppose qu'une jointure SQL inclut les
			// colonnes des genres
//	        List<Genre> genres = new ArrayList<>();
//	        do {
//	            int genreId = rs.getInt("genre_id");
//	            if (!rs.wasNull()) { // Vérifie si un genre est associé
//	                String libelle = rs.getString("libelle");
//	                genres.add(new Genre(genreId, libelle));
//	            }
//	        } while (rs.next() && rs.getInt("id") == jeu.getId()); // Continue à lire les genres associés au même jeu
//
//	        jeu.setGenres(genres);

			return jeu;
		}

	}

}
