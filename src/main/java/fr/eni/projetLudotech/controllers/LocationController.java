package fr.eni.projetLudotech.controllers;

import fr.eni.projetLudotech.bll.ClientService;
import fr.eni.projetLudotech.bll.locations.LocationService;
import fr.eni.projetLudotech.bo.Client;
import fr.eni.projetLudotech.bo.Jeu;
import fr.eni.projetLudotech.bo.Location;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class LocationController {

    private final LocationService locationService;
    private final ClientService clientService;

    @Autowired
    public LocationController(LocationService locationService, ClientService clientService) {
        this.locationService = locationService;
        this.clientService = clientService;
    }

    @ModelAttribute("location")
    public Location createJeu() {
        return new Location();
    }
    
   
    @GetMapping("/location/")
    public String displaylocationForm(Model model) {
        model.addAttribute("clients", clientService.findAllClients()); // Charger les clients pour la liste déroulante.
        return "location";
    }
    
//    @PostMapping("/location/searchClient")
//    public String searchClient(
//            @ModelAttribute("searchCriteria") Client searchCriteria,
//            Model model
//    ) {
//        // Rechercher les clients correspondants
//        List<Client> clients = clientService.searchClients(searchCriteria.getNom(), searchCriteria.getPrenom(), searchCriteria.getEmail());
//
//        // Ajouter les résultats au modèle
//        model.addAttribute("clients", clients);
//        model.addAttribute("location", new Location());
//        return "location";
//    }
}
