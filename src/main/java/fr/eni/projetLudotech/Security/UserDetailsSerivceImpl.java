package fr.eni.projetLudotech.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.eni.projetLudotech.bo.Utilisateur;
import fr.eni.projetLudotech.dal.users.UserRepository;

@Service
public class UserDetailsSerivceImpl implements UserDetailsService{
	
	Logger logger = LoggerFactory.getLogger(UserDetailsSerivceImpl.class);
	UserRepository userRepo;
	
	@Autowired
	public UserDetailsSerivceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	/*
	 * Est appelée à chaque connexion utilisateur
	 * username : login saisi par l'utilisateur
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails user =null;
		
		if(!userRepo.findByUserName(username).isEmpty()) {
			Utilisateur utilisateur = userRepo.findByUserName(username).get();	

			 	user = User.builder()
			 		.username(utilisateur.getUserName())
			 		.password(utilisateur.getPassword())  //A aller chercher en BD
			 		.roles(utilisateur.getRole())
			 		.build();
			 	logger.debug(user.toString());
				return user;
		}
		throw new UsernameNotFoundException(username + " not found.");
	}

}
