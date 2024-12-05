package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.dal.ClientRepository;
import fr.eni.projetLudotech.exceptions.ClientNotFoundException;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepo;
	
	
	public ClientServiceImpl() {
		super();
	}


	//@Autowired
	public ClientServiceImpl(ClientRepository clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}
	

	@Override
	public void add(Client client) {
		clientRepo.create(client);
	}

	@Override
	public List<Client> findAllClients() {
		return clientRepo.findAllClients();
	}


	@Override
	public Optional<Client> findClientById(int id) {
		return clientRepo.findClientById(id);
	}


	@Override
	public void update(Client client) throws ClientNotFoundException {
   	 Optional<Client> clientOpt = findClientById(client.getId());
        if (clientOpt.isPresent()) {
       	 clientRepo.update(client);	 
        }else {
       	 //TODO gerer l'erreur
       	 throw new ClientNotFoundException();
        }
       
   }


	@Override
	public void delete(int id) {
		clientRepo.delete(id);
		
	}
	
	@Override
	public void save(Client entity) throws ClientNotFoundException {
		
		if(entity.getId()==null) {
			this.add(entity);
			return;
		}
		this.update(entity);
		
	}

}
