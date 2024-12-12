package fr.eni.projetLudotech.bll.locations;

import java.util.List;

import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.ExemplaireJeu;
import fr.eni.projetLudotech.bo.Location;

public interface LocationService {
	public void create(Location location, Client client, List<ExemplaireJeu> jeux);
	public void update(Location location);
	public float calculerPrix(Location location);
	
}
