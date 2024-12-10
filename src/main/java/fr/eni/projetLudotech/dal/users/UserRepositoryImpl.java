package fr.eni.projetLudotech.dal.users;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import fr.eni.projetLudotech.bo.Utilisateur;

@Repository
public class UserRepositoryImpl implements UserRepository {

	Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public Optional<Utilisateur> findByUserName(String userName) {
	    String sql = "SELECT userName, password, role FROM Utilisateurs WHERE userName = ?";
	    
	    try {
	    	Utilisateur user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Utilisateur.class), userName);
	        return Optional.ofNullable(user);
	    } catch (EmptyResultDataAccessException e) {
	        return Optional.empty();
	    }
	}
	
}
