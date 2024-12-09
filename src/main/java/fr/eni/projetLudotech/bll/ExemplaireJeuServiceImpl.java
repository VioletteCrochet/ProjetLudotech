package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.projetLudotech.bo.ExemplaireJeu;
import fr.eni.projetLudotech.dal.ExemplaireRepository;

@Service("exemplaireService")
public class ExemplaireJeuServiceImpl implements ExemplaireJeuService{
	
	@Autowired
	private ExemplaireRepository exemplaireRepo;
	
	
	public ExemplaireJeuServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExemplaireJeuServiceImpl(ExemplaireRepository exemplaireRepo) {
		super();
		this.exemplaireRepo = exemplaireRepo;
	}

	@Override
	public void add(ExemplaireJeu exemplaireJeu, Integer jeuId) {
		exemplaireJeu.setJeuId(jeuId);
		exemplaireRepo.create(exemplaireJeu);
	}
	
	public List<ExemplaireJeu> findExemplaireByJeuId(Integer JeuId){
		
		return exemplaireRepo.findExemplaireByJeuid(JeuId);	
	}

	@Override
	public Optional<ExemplaireJeu> findExemplaireById(Integer id) {
		return exemplaireRepo.findExemplaireById(id);
	}

	@Override
	public void update(ExemplaireJeu exemplaireJeu) {
		exemplaireRepo.update(exemplaireJeu);
		
	}
	
	
	
}
