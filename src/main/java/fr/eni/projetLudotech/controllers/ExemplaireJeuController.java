package fr.eni.projetLudotech.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.projetLudotech.bll.ExemplaireJeuService;
import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.ExemplaireJeu;
import fr.eni.projetLudotech.bo.Jeu;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class ExemplaireJeuController {

	Logger logger = LoggerFactory.getLogger(ExemplaireJeuController.class);
	private ExemplaireJeuService service;

	public ExemplaireJeuController(ExemplaireJeuService service) {
		super();
		this.service = service;
	}

	@ModelAttribute("exemplaire")
	public ExemplaireJeu createExemplaireJeu() {
		ExemplaireJeu e = new ExemplaireJeu();
		e.setCodeBarre("");
		e.setLouable(true);
		e.setJeuId(0);
		return e;
	}

	@GetMapping("exemplaire/{id}")
	public String displayExemplaire(Model model, @PathVariable(name="id")int id) {
		Optional<ExemplaireJeu> exemplaire = service.findExemplaireById(id);
		model.addAttribute("exemplaire", exemplaire.get());
		logger.debug(exemplaire.toString());
		return "exemplaireDetail";
	}

	@PostMapping("addExemplaire/{jeuId}")
	public String addExemplairetoJeu(@Valid @ModelAttribute("exemplaire") ExemplaireJeu exemplaireJeu,
			BindingResult result, @PathVariable(name = "jeuId") int jeuId, Model model,
			RedirectAttributes redirectAttr) {
		int id = jeuId;
		if (result.hasErrors()) {
			redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.exemplaire", result);
			redirectAttr.addFlashAttribute("exemplaire", exemplaireJeu);
			return "redirect:/jeu/" + id; // Optional: Return to a form view to fix errors
		}
		service.add(exemplaireJeu, jeuId);
		return "redirect:/jeu/" + id;

	}

}
