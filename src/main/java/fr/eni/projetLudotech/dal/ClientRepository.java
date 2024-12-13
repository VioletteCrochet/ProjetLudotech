package fr.eni.projetLudotech.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.exceptions.ClientNotFoundException;

public interface ClientRepository {

	void create(Client client);
	public List<Client> findAllClients();
	public Optional<Client> findClientById(int id) throws ClientNotFoundException;
	void update(Client client);
	public void delete(int id);
	

	
}
