package com.fstg.mediatech.controllers;
import static  com.fstg.mediatech.GestionCompteBancaireApplication.comptes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fstg.mediatech.entities.Compte;

@Controller
public class BanqueController {
		@RequestMapping("comptes")
		public String afficher(Model model) {
			model.addAttribute("comptes", comptes);
			return "listeComptes";
			}
		
		@GetMapping("ajouter")
		public String add() {
		
			return"ajouterCompte";	
		}
		
		@PostMapping("ajouter")
	    public String ajouter(@RequestParam("titulaire") String titulaire, @RequestParam("solde") double solde) {
			int id = comptes.size();
			id++;
	         Compte c = new Compte(titulaire,solde);
			c.setId(id);
	     comptes.add(c);
	     
		 return "redirect:comptes"; }
		
		@GetMapping("details/{id}")
		public String supprimer(@PathVariable("id") int id, Model model) {
			for(Compte c : comptes) {
				if(c.getId()==id) {
				 model.addAttribute("compte",c)	;	}}
			
			return "detailsCompte";}
		
		@PostMapping("comptes/{id}/retrait")
		public String retrait(@PathVariable("id") int id, @RequestParam("montant_retrait") double montant_retrait) {
			
			for(Compte c : comptes) {
				if(c.getId()==id && montant_retrait>0 && c.getSolde()>montant_retrait) {
					c.setSolde(c.getSolde()-montant_retrait);
					}
		}
	     	return"redirect:../../comptes"; }
			
		@PostMapping("comptes/{id}/depot")
		public String depot(@PathVariable("id") int id, @RequestParam("montant_depot") double montant_depot) {
			
			for(Compte c : comptes) {
				if (c.getId() == id && montant_depot > 0) {
					c.setSolde(c.getSolde()+montant_depot);
				}}
     		return"redirect:../../comptes"; } 
        @RequestMapping("/")
        public String home() {
          return "redirect:/index.html";  
}}