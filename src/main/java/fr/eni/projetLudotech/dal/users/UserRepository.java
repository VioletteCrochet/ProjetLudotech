package fr.eni.projetLudotech.dal.users;

import java.util.Optional;

import fr.eni.projetLudotech.bo.Utilisateur;

public interface UserRepository {
	public Optional<Utilisateur> findByUserName(String UserName);
}
