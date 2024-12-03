package fr.eni.projetLudotech.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projetLudotech.bo.Client;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public ClientRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void create(Client client) {
		// Requête SQL d'insertion
		String sql = "insert into Clients (nom, prenom, email, numTel, rue, cpo, ville) "
				+ "values (:nom, :prenom, :email, :numTel, :rue, :cpo, :ville)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		// Utilisation de BeanPropertySqlParameterSource pour associer les valeurs
		// automatiquement
		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(client), keyHolder, new String[]{"id"});
		client.setId(keyHolder.getKeyAs(Integer.class));
		// Vérification que l'insertion a bien eu lieu
		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été ajoutée pour le client: " + client);
		}
	}
	
	@Override
	public List<Client> findAllClients() {
		String sql = "select id, nom, prenom, email, numTel, rue, cpo, ville from Clients";
		List<Client> clients = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<Client>(Client.class));
		return clients;
	}

	@Override
	public Optional<Client> findClientById(int id) {
		String sql = "select id, nom, prenom, email, numTel, rue, cpo, ville from Clients where id = :id";

		// Création du Map pour les paramètres nommés
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		try {
			// Requête avec un BeanPropertyRowMapper pour mapper les résultats à un objet
			// Client
			Client client = namedParameterJdbcTemplate.queryForObject(sql, params,
					new BeanPropertyRowMapper<>(Client.class));
			return Optional.ofNullable(client); // Retourne un Optional
		} catch (EmptyResultDataAccessException e) {
			// Si aucun client n'est trouvé, retourne Optional.empty()
			return Optional.empty();
		}
	}

	@Override
	public void update(Client client) {
		String sql = "update Clients set  nom = :nom, prenom = :prenom, email = :email, numTel = :numTel, rue = :rue, cpo = :cpo, ville = :ville where id = :id;";

		// Utilisation de BeanPropertySqlParameterSource pour associer les paramètres
	    int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(client));
	    
	    if (nbRows != 1) {
	        throw new RuntimeException("Aucune ligne n'a été mise à jour pour le client avec l'id: " + client.getId());
	    }
	}
	
	public void delete(int id) {
		// Requête SQL de suppression
		String sql = "delete from Clients where id = :id";

		// Création du paramètre pour la requête
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		// Exécution de la requête
		int nbRows = namedParameterJdbcTemplate.update(sql, params);

		// Vérification que la suppression a bien eu lieu
		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été supprimée pour le client avec l'id : " + id);
		}
	}

	//getters and setters
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}
