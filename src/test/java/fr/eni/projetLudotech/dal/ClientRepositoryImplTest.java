package fr.eni.projetLudotech.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.exceptions.ClientNotFoundException;

@SpringBootTest
public class ClientRepositoryImplTest {
	
	@MockitoBean
	private ClientRepository clientRepo;
	
	@Test
	public void testFindClientById_ClientFound() throws ClientNotFoundException {
		//Arrange
		int id = 22;
		Client clientAttendu = new Client (22, "Stalis", "Jayce", "jayce.stalis@gmail.com", "0214253698", "avenue du roi", "44660", "Piltover");
		when(clientRepo.findClientById(id)).thenReturn(Optional.of(clientAttendu));
		
		//act
		Client result = clientRepo.findClientById(id).get();
		
		//assert	
		assertNotNull(result);
		assertEquals(clientAttendu.getNom(), result.getId());
		assertEquals(clientAttendu.getPrenom(), result.getId());
		assertEquals(clientAttendu.getEmail(), result.getId());
		assertEquals(clientAttendu.getRue(), result.getId());
		assertEquals(clientAttendu.getCpo(), result.getId());
		assertEquals(clientAttendu.getVille(), result.getId());
		verify(clientRepo, times(1)).findClientById(id);

	}
	
	@Test
	public void testFindClientById_CLientNotFound() throws ClientNotFoundException {
		//Arrange
		int id = 99999;
		when(clientRepo.findClientById(id)).thenReturn(Optional.empty());
		
		//act
		Client result = clientRepo.findClientById(id).get();
		
		//assert	
		 assertThrows(ClientNotFoundException.class, () -> {
	            clientRepo.findClientById(id);
	        });
	        verify(clientRepo, times(1)).findClientById(id);
	}
}
