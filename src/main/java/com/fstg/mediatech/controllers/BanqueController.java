package com.fstg.mediatech.controllers;

import static com.fstg.mediatech.GestionCompteBancaireApplication.comptes;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fstg.mediatech.Repositories.CompteRepository;
import com.fstg.mediatech.entities.Compte;

@Controller
public class BanqueController {
	
	private final CompteRepository compteRepository;
	//@Autowired
	public BanqueController(CompteRepository compteRepository)
	{
		this.compteRepository=compteRepository;
	}
	
	
	@RequestMapping("comptes")
	public String afficher(Model model) {
		List<Compte>comptes=compteRepository.findAll();
		model.addAttribute("comptes", comptes);
		return "listeComptes";
	}
	

	@GetMapping("ajouter")
	public String add(Model model) {
		model.addAttribute("compte",new Compte());
		return "ajouterCompte";
	}

	@PostMapping("ajouter")
	public String ajouter(@ModelAttribute Compte compte) {
		compteRepository.save(compte);

		return "redirect:comptes";
	}

	@GetMapping("details/{id}")
	public String supprimer(@PathVariable("id") long id, Model model) {
		Compte compte= compteRepository.getById(id);
 		
		model.addAttribute("compte",compte);

		return "detailsCompte";
	}

	@PostMapping("comptes/{id}/retrait")
	public String retrait(@PathVariable("id") long id, @RequestParam("montant_retrait") double montant_retrait) {

	    boolean compteExists = compteRepository.existsById(id); // Check if the account exists

	    if (compteExists) {
	        Compte c = compteRepository.getById(id); 

	        if (c.getSolde() >= montant_retrait && montant_retrait > 0) {
	            c.setSolde(c.getSolde() - montant_retrait);
	            compteRepository.save(c);
	        }
	    }

	    return "redirect:../../comptes"; // Corrected redirection path
	}


	@PostMapping("comptes/{id}/depot")
	public String depot(@PathVariable("id") long id, @RequestParam("montant_depot") double montant_depot) {

	    boolean compteExists = compteRepository.existsById(id); // Check if the account exists

	    if (compteExists && montant_depot > 0) {
	        Compte c = compteRepository.getById(id); 
	        
	        c.setSolde(c.getSolde() + montant_depot);
	        compteRepository.save(c); 
	    }

	    return "redirect:../../comptes"; 
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/index.html";
	}
}