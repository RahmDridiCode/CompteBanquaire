package com.fstg.mediatech.controllers;

import com.fstg.mediatech.dto.RegistrationForm;
import com.fstg.mediatech.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
	@Autowired
	private AuthService authService;

	@GetMapping("/signup")
	public String showSignUpForm(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "signup";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute RegistrationForm form) {
		authService.registerUser(form);
		return "redirect:/login?success";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
