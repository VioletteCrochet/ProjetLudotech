package fr.eni.projetLudotech.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice(annotations = Controller.class)
public class ludotechAdviceController {
	
	Logger logger = LoggerFactory.getLogger(ludotechAdviceController.class);
	
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public String handleException(Exception ex, Model model) {
		
		logger.error(ex.getMessage());
		model.addAttribute("body", "erreur");
		return "index";
		
	}
	
	
}
