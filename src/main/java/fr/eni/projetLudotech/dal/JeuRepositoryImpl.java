package fr.eni.projetLudotech.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import fr.eni.projetLudotech.bo.Jeu;

public class JeuRepositoryImpl implements JeuRepository{

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public JeuRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public void create(Jeu jeu) {
		// Requête SQL d'insertion
				String sql = "insert into Jeux (titre, reference, description, tarifJour, ageMin, duree) "
						+ "values (:titre, :reference, :description, :tarifjour, :ageMin, :duree)";

				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				// Utilisation de BeanPropertySqlParameterSource pour associer les valeurs
				// automatiquement
				int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(jeu), keyHolder, new String[]{"id"});
				jeu.setId(keyHolder.getKeyAs(Integer.class));
				// Vérification que l'insertion a bien eu lieu
				if (nbRows != 1) {
					throw new RuntimeException("Aucune ligne n'a été ajoutée pour le client: " + jeu);
				}
			}

	@Override
	public List<Jeu> findAllJeux() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Jeu> findJeuById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Jeu jeu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
