package fr.eni.projetLudotech.dal.locations;

import java.util.List;
import java.util.Optional;

import fr.eni.projetLudotech.bo.Location;

public class LocationRepositoryImpl implements LocationRepository{

	@Override
	public void create(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Location> findAllLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> findLocationsByClientID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Location> findLocationById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Location> findCurrentLocationByClientId(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	

}
