package fr.eni.projetLudotech.bll;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.projetLudotech.bo.Jeu;
import fr.eni.projetLudotech.dal.JeuRepository;

@Service
public class JeuServiceImpl implements JeuService{
	
	Logger logger = LoggerFactory.getLogger(JeuServiceImpl.class);
	@Autowired
	private JeuRepository jeuRepo;
	
	public JeuServiceImpl() {
		super();
	}
	//@Autowired
		public JeuServiceImpl(JeuRepository jeuRepo) {
			super();
			this.jeuRepo = jeuRepo;
		}
	
	@Override
	public List<Jeu> findAllJeux() {
		
		return jeuRepo.findAllJeux();
	}

	@Override
	public Optional<Jeu> findById(Integer id) {
		return jeuRepo.findJeuById(id);
	}

	@Override
	public void update(Jeu jeu) {
		Optional<Jeu> jeuOpt = findById(jeu.getId());
        if (jeuOpt.isPresent()) {
        	jeuRepo.update(jeu);	 
        }
		
	}

	@Override
	public void delete(Integer id) {
		jeuRepo.delete(id);
		
	}

	@Override
	public void save(Jeu jeu) {
		
		if(jeu.getId()==null) {
			this.add(jeu);
			return;
		}
		this.update(jeu);
		
		
	}

	@Override
	public void add(Jeu jeu) {
		jeuRepo.create(jeu);
		logger.debug("requête addJeu passée au service");
	}
}


