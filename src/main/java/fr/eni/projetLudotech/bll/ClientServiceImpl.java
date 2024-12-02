package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.dal.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepo;
	
	//@Autowired
	public ClientServiceImpl(ClientRepository clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}
	

	@Override
	public Client add(Client client) {
		clientRepo.create(client);
		return null;
	}

	@Override
	public void deleteClient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> findAllClients() {
		return clientRepo.findAllClients();
	}


	@Override
	public Optional<Client> findClientById(int idClient) {
		return clientRepo.findClientById(idClient);
	}


	@Override
	public void update(Client client) {
		 Optional<Client> oldClientOptional = findClientById(client.getId());
	        if (oldClientOptional.isPresent()) {
	        	
	            Client oldClient = oldClientOptional.get();
	            BeanUtils.copyProperties(client, oldClient);
	        }
	}


	@Override
	public void delete(int id) {
		clientRepo.delete(id);
		
	}
	
	@Override
	public void save(Client entity) {
		
		if(entity.getId()==null) {
			this.add(entity);
			return;
		}
		this.update(entity);
		
	}

}
