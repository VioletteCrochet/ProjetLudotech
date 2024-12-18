package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Genre;
import fr.eni.projetLudotech.exceptions.ClientNotFoundException;

public interface ClientService {
	
	List<Client> findAllClients();
	public Optional<Client> findClientById(int id);
	public void update(Client client) throws ClientNotFoundException;
	public void delete(int id);
	public void save(Client entity) throws ClientNotFoundException;
	public void add(Client client);

	

	
	
}
