package fr.eni.projetLudotech.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.projetLudotech.bll.ClientService;
import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.exceptions.ClientNotFoundException;
import jakarta.validation.Valid;

@Controller
public class ClientController {
	
	Logger logger = LoggerFactory.getLogger(ClientController.class);
	private ClientService service;

	@Autowired
	public ClientController(ClientService service) {
		super();
		this.service = service;
	}

	@ModelAttribute("client")
	public Client createClient() {
		Client c = new Client();
		c.setNom("");
		c.setPrenom("");
		c.setEmail("");
		c.setNumTel("");
		c.setRue("");
		c.setCpo("");
		c.setVille("");
		return c;
	}

	@GetMapping("/clients")
	public String getClients(Model model) {
		List<Client> clients = service.findAllClients();
		model.addAttribute("clients", clients);
		return "/clients";

	}

	@GetMapping("/client/{id}")
	public String displayDetails(@PathVariable(name = "id") int id, Model model) {
		Optional<Client> client = service.findClientById(id);
		if (client.isPresent()) {
			model.addAttribute("client", client.get());
		} else {
			model.addAttribute("error", "Le client avec l'ID " + id + " n'a pas été trouvé.");
		}
		return "clientDetail";
	}

	@PostMapping("/updateClient/{id}")
	public String updateClient(@PathVariable(name = "id") int id, @Valid @ModelAttribute("client") Client client,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.warn("erreur de saisie, client non modifié");
			model.addAttribute("client", client); 
			return "clientDetail"; // Recharger la vue actuelle avec les erreurs
			
		}

		try {
			service.update(client);
		} catch (ClientNotFoundException e) {
			model.addAttribute("errorMessage", "Le client n'existe pas.");
			return "clientDetail"; // Recharger avec le message d'erreur
		}

		return "redirect:/client/" + id; // Rediriger après succès
	}

	@GetMapping("/deleteClient/{id}")
	public String updateClient(@PathVariable(name = "id") int id, Model model) {
		service.delete(id);
		return "redirect:/clients";
	}

	@PostMapping("/addClient")
	public String addClient(@Valid @ModelAttribute("client") Client client, BindingResult result,
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			// Stocker les erreurs et les données du formulaire pour la redirection
			redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.client", result);
			redirectAttr.addFlashAttribute("client", client);
		}

		service.add(client);
		redirectAttr.addFlashAttribute("successMessage", "Client ajouté avec succès !");
		return "redirect:/clients";
	}

}
