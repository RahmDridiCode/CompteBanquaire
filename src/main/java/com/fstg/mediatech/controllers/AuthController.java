package com.fstg.mediatech.controllers;

import com.fstg.mediatech.entities.PasswordResetToken;
import com.fstg.mediatech.entities.User;
import com.fstg.mediatech.repositories.PasswordResetTokenRepository;
import com.fstg.mediatech.repositories.UserRepository;
import com.fstg.mediatech.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class AuthController {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final PasswordResetTokenRepository passwordResetTokenRepository;
	private final EmailService emailService;

	@GetMapping("/signup")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
		user.setRoles(Set.of("ROLE_USER"));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/forgot-password")
	public String showForgotPasswordForm(Model model) {
		model.addAttribute("email", "");
		return "forgot-password";
	}

	@PostMapping("/forgot-password")
	public String processForgotPassword(@RequestParam String email, Model model) {
		Optional<User> userOptional = userRepository.findByUsername(email);

		if (userOptional.isPresent()) {
			User user = userOptional.get();

			// Generate token
			String token = UUID.randomUUID().toString();

			// Create and save token
			PasswordResetToken resetToken = new PasswordResetToken();
			resetToken.setToken(token);
			resetToken.setUser(user);
			resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));
			passwordResetTokenRepository.save(resetToken);

			// Send email
			emailService.sendPasswordResetEmail(user.getUsername(), token);
			model.addAttribute("message", "a reset link has been sent to your email.");

		}else {
			model.addAttribute("message", "Account not found!");
		}

		return "forgot-password";
	}
	@PostMapping("/reset-password")
	public String handlePasswordReset(@RequestParam String token, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
		if (!password.equals(confirmPassword)) {



			model.addAttribute("error", "Passwords do not match.");
			model.addAttribute("token", token); // So the form keeps the token
			return "reset-password";
		}
		Optional<PasswordResetToken> tokenOptional = passwordResetTokenRepository.findByToken(token);

		if (tokenOptional.isPresent() && tokenOptional.get().getExpiryDate().isAfter(LocalDateTime.now())) {
			User user = tokenOptional.get().getUser();
			user.setPassword(passwordEncoder.encode(password));
			userRepository.save(user);
			passwordResetTokenRepository.delete(tokenOptional.get());

			return "redirect:/login?resetSuccess";
		}

		return "redirect:/reset-password?token=" + token + "&error=invalid";
	}
	@GetMapping("/reset-password")
	public String showResetPasswordForm(@RequestParam String token, Model model) {
		model.addAttribute("token", token);
		return "reset-password";
	}
	
}
