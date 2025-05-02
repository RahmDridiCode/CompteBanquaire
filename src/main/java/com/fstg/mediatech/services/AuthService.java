package com.fstg.mediatech.services;

import com.fstg.mediatech.dto.RegistrationForm;
import com.fstg.mediatech.entities.User;
import com.fstg.mediatech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setRoles(Set.of("ROLE_USER"));
        userRepository.save(user);
    }

}
