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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.projetLudotech.bll.JeuService;
import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Jeu;
import jakarta.validation.Valid;

@Controller
public class JeuController {

    Logger logger = LoggerFactory.getLogger(JeuController.class);
    private final JeuService service;

    public JeuController(JeuService service) {
        this.service = service;
    }

    // Préparation d'un nouvel objet Jeu
    @ModelAttribute("jeu")
    public Jeu createJeu() {
        return new Jeu();
    }

    // Afficher la liste des jeux
    @GetMapping("/jeux")
    public String getJeux(Model model) {
        List<Jeu> jeux = service.findAllJeux();
        model.addAttribute("jeux", jeux);
        return "jeux"; // Vue pour la liste des jeux
    }

    // Afficher les détails d'un jeu
    @GetMapping("/jeu/{id}")
    public String displayDetails(@PathVariable(name = "id") int id, Model model) {
        Optional<Jeu> jeu = service.findById(id);
        if (jeu.isPresent()) {
            model.addAttribute("jeu", jeu.get());
        } else {
            model.addAttribute("error", "Le jeu avec l'ID " + id + " n'a pas été trouvé.");
        }
        return "jeuDetail"; // Vue pour les détails du jeu
    }

    // Formulaire pour ajouter un nouveau jeu
    @PostMapping("/addJeu")
    public String addJeuForm(@Valid @ModelAttribute("jeu") Jeu jeu, BindingResult result,
			RedirectAttributes redirectAttr) {
    	if (result.hasErrors()) {
			redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.client", result);
			redirectAttr.addFlashAttribute("jeu", jeu);
			return "redirect:/jeux";
		}
        service.add(jeu);
        return "redirect:/jeux"; // Vue pour ajouter ou modifier un jeu
    }

    // Sauvegarder un nouveau jeu ou mettre à jour un jeu existant
    @PostMapping("/updatejeu")
    public String saveJeu(@Valid @ModelAttribute("jeu") Jeu jeu, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "jeuDetail"; // Retour au formulaire en cas d'erreur
        }
        service.save(jeu);
        return "redirect:/jeux"; // Redirection vers la liste des jeux
    }

    // Formulaire pour modifier un jeu existant
    @GetMapping("/jeu/edit/{id}")
    public String editJeuForm(@PathVariable(name = "id") int id, Model model) {
        Optional<Jeu> jeu = service.findById(id);
        if (jeu.isPresent()) {
            model.addAttribute("jeu", jeu.get());
            return "jeuForm"; // Réutilisation du même formulaire
        }
        model.addAttribute("error", "Le jeu avec l'ID " + id + " n'a pas été trouvé.");
        return "redirect:/jeux";
    }

    // Supprimer un jeu
    @GetMapping("/deleteJeu/{id}")
    public String deleteJeu(@PathVariable(name = "id") int id, Model model) {
        service.delete(id);
        return "redirect:/jeux";
    }
}
