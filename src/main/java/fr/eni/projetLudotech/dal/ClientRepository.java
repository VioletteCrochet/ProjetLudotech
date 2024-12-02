package fr.eni.projetLudotech.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Client;

public interface ClientRepository {

	void create(Client client);
	public List<Client> findAllClients();
	public Optional<Client> findClientById(int id);
	void update(Client client);
	public void delete(int id);
	

	
}
