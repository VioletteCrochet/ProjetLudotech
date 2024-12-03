package fr.eni.projetLudotech.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.projetLudotech.bll.JeuService;
import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Jeu;
import fr.eni.projetLudotech.exceptions.ClientNotFoundException;
import jakarta.validation.Valid;

@Controller
public class JeuController {
	Logger logger = LoggerFactory.getLogger(JeuController.class);
	private JeuService service;
	
	public JeuController(JeuService service) {
		super();
		this.service = service;
	}

	@ModelAttribute("jeu")
	public Jeu createJeu() {
		Jeu j = new Jeu();
		j.setTitre("");
		j.setReference("");
		j.setDescription("");
		j.setTarifJour(0);
		j.setAgeMin(0);
		j.setDuree(0);
		return j;
	}
	@GetMapping("/jeux")
	public String getClients(Model model) {
		List<Jeu> jeux = service.findAllJeux();
		model.addAttribute("jeux", jeux);
		return "/jeux";

	}
	@GetMapping("/jeu/{id}")
	public String displayDetails(@PathVariable(name = "id") int id, Model model) {
		Optional<Jeu> jeu = service.findById(id);
		if (jeu.isPresent()) {
			model.addAttribute("jeu", jeu.get());
		} else {
			model.addAttribute("error", "Le jeu avec l'ID " + id + " n'a pas été trouvé.");
		}
		return "jeuDetail";
	}
	
	
}
