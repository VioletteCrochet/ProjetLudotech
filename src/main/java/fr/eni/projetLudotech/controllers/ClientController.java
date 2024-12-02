package fr.eni.projetLudotech.controllers;

import java.util.List;
import java.util.Optional;

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
import jakarta.validation.Valid;

@Controller
public class ClientController {

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
	public String updateClient(@PathVariable(name = "id") int id, Model model, Client client) {
		service.update(client);
		return "redirect:/client/{id}";
	}

	@GetMapping("/deleteClient/{id}")
	public String updateClient(@PathVariable(name = "id") int id, Model model) {
		service.delete(id);
		return "redirect:/clients";
	}

	@PostMapping("/addClient")
	public String addClient(@Valid Client client, BindingResult result, RedirectAttributes redirectAttr) {
	    if (result.hasErrors()) {
	        redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.client", result);
	        redirectAttr.addFlashAttribute("client", client);
	        return "redirect:/clients";
	    }

	    service.add(client);
	    return "redirect:/clients";
	}

}
