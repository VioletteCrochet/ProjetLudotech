package fr.eni.projetLudotech.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.projetLudotech.bo.Genre;
import fr.eni.projetLudotech.bo.Jeu;

@Repository
public class JeuRepositoryImpl implements JeuRepository {
	
	Logger logger = LoggerFactory.getLogger(JeuRepositoryImpl.class);
	
	private final GenreRepository genreRepository;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public JeuRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, GenreRepository genreRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        this.genreRepository = genreRepository;
    }

	@Override
	@Transactional
	public void create(Jeu jeu) {

		String sql = "insert into Jeux (titre, reference, description, tarifJour, ageMin, duree) "
				+ "values (:titre, :reference, :description, :tarifJour, :ageMin, :duree)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu), keyHolder,
				new String[] { "id" });
		jeu.setId(keyHolder.getKeyAs(Integer.class));

		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été ajoutée pour le jeu: " + jeu);
		}
		
		// Ajouter les genres associés après la création du jeu
        if (jeu.getGenres() != null && !jeu.getGenres().isEmpty()) {
            for (Genre genre : jeu.getGenres()) {
                linkGenreToJeu(jeu.getId(), genre.getId());
            }
            
        }
	}
	
	// Méthode pour lier un genre à un jeu dans la table Jeux_Genres
    private void linkGenreToJeu(Integer jeuId, Integer genreId) {
        String sql = "insert into Jeux_Genres (jeu_id, genre_id) values (:jeuId, :genreId)";
        Map<String, Object> params = new HashMap<>();
        params.put("jeuId", jeuId);
        params.put("genreId", genreId);
        namedParameterJdbcTemplate.update(sql, params);
    }
    // Méthode pour supprimer les genres associés à un jeu
    private void removeGenresFromJeu(Integer jeuId) {
        String sql = "delete from Jeux_Genres where jeu_id = :jeuId";
        Map<String, Object> params = new HashMap<>();
        params.put("jeuId", jeuId);
        namedParameterJdbcTemplate.update(sql, params);
    }

	@Override
	public List<Jeu> findAllJeux() {
		String sql = "select id, titre, reference, description, tarifJour, ageMin, duree from Jeux";
		List<Jeu> jeux = namedParameterJdbcTemplate.query(sql, new JeuRowMapper());
		// Récupérer les genres associés pour chaque jeu
        for (Jeu jeu : jeux) {
            jeu.setGenres(genreRepository.findGenresByJeuId(jeu.getId()));
            logger.debug(jeu.toString());
        }
		return jeux;
	}

	@Override
	public Optional<Jeu> findJeuById(Integer id) {
		String sql = "select id, titre, reference, description, tarifJour, ageMin, duree from Jeux where id = :id";

		// Création du Map pour les paramètres nommés
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		try {
			Jeu jeu = namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Jeu.class));
			// Récupérer les genres associés au jeu
            jeu.setGenres(genreRepository.findGenresByJeuId(jeu.getId()));
			return Optional.ofNullable(jeu);
		} catch (EmptyResultDataAccessException e) {

			return Optional.empty();
		}

		// Jeu jeu = jdbcTemplate.queryForObject(sql, new JeuRowMapper(), id);
		// return Optional.ofNullable(jeu);

	}

	@Override
	public void update(Jeu jeu) {
		String sql = "update Jeux set titre = :titre, reference = :reference, description = :description, tarifJour = :tarifJour, ageMin = :ageMin, duree = :duree where id = :id";

		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu));

		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été mise à jour pour le client avec l'id: " + jeu.getId());
		}
		
		// Mettre à jour les genres associés
        if (jeu.getGenres() != null && !jeu.getGenres().isEmpty()) {
            // On supprime d'abord les genres existants et on les réassocie
            removeGenresFromJeu(jeu.getId());
            for (Genre genre : jeu.getGenres()) {
                linkGenreToJeu(jeu.getId(), genre.getId());
            }
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
		
		// Supprimer les liens entre le jeu et ses genres
        removeGenresFromJeu(id);

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
