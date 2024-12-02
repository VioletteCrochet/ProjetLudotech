package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Client;

public interface ClientService {
	
	List<Client> findAllClients();
	
	
	public void deleteClient();
	public Optional<Client> findClientById(int id);
	public void update(Client client);
	public void delete(int id);
	public void save(Client entity);
	public Client add(Client client);

	

	
	
}
