package fr.eni.projetLudotech.bll.locations;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.DetailLocation;
import fr.eni.projetLudotech.bo.ExemplaireJeu;
import fr.eni.projetLudotech.bo.Jeu;
import fr.eni.projetLudotech.bo.Location;
import fr.eni.projetLudotech.dal.ClientRepository;
import fr.eni.projetLudotech.dal.ExemplaireRepository;
import fr.eni.projetLudotech.dal.JeuRepository;
import fr.eni.projetLudotech.dal.locations.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	private LocationRepository locaRepo;
	private ClientRepository clientRepo;
	private ExemplaireRepository exemplaireRepo;
	private JeuRepository jeuRepo;

	@Override
	public void create(Location location, Client client, List<ExemplaireJeu> jeux) {
		//AtomicInteger pour gérer un compteur dans un stream(merci ChatGPT)
		AtomicInteger index = new AtomicInteger(0); 
		location.setDateDebutLocation(LocalDate.now());
		location.setPayed(false);
		location.setTotalPrice(0.00f);
		location.setClient(client);
		// affecter à la location sa liste de détails
		location.setDetails(jeux.stream()
				.map(j -> new DetailLocation(
						index.incrementAndGet(),																				// détail
						jeuRepo.findJeuById(j.getJeuId()).get().getTarifJour(), 
						location,
						j)).toList());																					// détails sous
																									// forme de liste

//		jeux.stream().forEach(j->location.addDetail(
//				new DetailLocation(
//						++i,
//						jeuRepo.findJeuById(j.getJeuId()).get().getTarifJour(),
//						location,
//						jeux.get(i))));

	}

	@Override
	public void update(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public float calculerPrix(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

}
