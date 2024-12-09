package fr.eni.projetLudotech.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fr.eni.projetLudotech.bo.ExemplaireJeu;
import fr.eni.projetLudotech.bo.Genre;

@Repository
public class ExemplaireRepositoryImpl implements ExemplaireRepository {

	Logger logger = LoggerFactory.getLogger(ExemplaireRepositoryImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	public ExemplaireRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
	}

	@Override
	public void create(ExemplaireJeu exemplaire) {
		String sql = "insert into ExemplaireJeux (codeBarre, louable, jeuId) values  (:codeBarre, :louable, :jeuId)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(exemplaire), keyHolder,
				new String[] { "id" });
		exemplaire.setId(keyHolder.getKeyAs(Integer.class));

		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été ajoutée pour l'exemplaire: " + exemplaire);
		}
	}

	@Override
	public void update(ExemplaireJeu exemplaire) {
		String sql = "update ExemplaireJeux set codeBarre = :codeBarre, louable = :louable where id = :id;";

		int nbRows = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(exemplaire));

		if (nbRows != 1) {
			throw new RuntimeException("Aucune ligne n'a été mise à jour pour le client avec l'id: " + exemplaire.getId());
		}

	}

	@Override
	public List<ExemplaireJeu> findExemplaireByJeuid(Integer id) {
		String sql = "select id, codeBarre, louable from ExemplaireJeux where jeuId = ?";
		List<ExemplaireJeu> exemplaires = jdbcTemplate.query(sql, new ExemplaireRowMapper(), id);
		return exemplaires;
	}

	@Override
	public Optional<ExemplaireJeu> findExemplaireById(Integer id) {
		String sql = "select id, codebarre, louable, jeuId from ExemplaireJeux where id = ?";
		ExemplaireJeu exemplaire = jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper<ExemplaireJeu>(ExemplaireJeu.class), id);
		return Optional.ofNullable(exemplaire);
	}

	private static class ExemplaireRowMapper implements RowMapper<ExemplaireJeu> {
		@Override
		public ExemplaireJeu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExemplaireJeu exemplaire = new ExemplaireJeu();
			exemplaire.setId(rs.getInt("id"));
			exemplaire.setCodeBarre(rs.getString("codeBarre"));
			exemplaire.setLouable(rs.getBoolean("louable"));

			return exemplaire;
		}
	}
}
