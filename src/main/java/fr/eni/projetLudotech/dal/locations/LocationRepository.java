package fr.eni.projetLudotech.dal.locations;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Location;

public interface LocationRepository {
	void create(Location location);
	public List<Location> findAllLocations();
	public List<Location> findLocationsByClientID();
	public Optional<Location> findLocationById(Integer id);
	public Optional<Location> findCurrentLocationByClientId(Integer id);
	void update(Location location);
	public void delete(Integer id);
}
