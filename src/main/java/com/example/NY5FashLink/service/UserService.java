package com.example.NY5FashLink.service;

import com.example.NY5FashLink.model.*;
import com.example.NY5FashLink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Sign Up
    public void registerUser(UserRegistrationDTO registrationDTO) {
        User user = new User();

        // Set common fields
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPhone(registrationDTO.getPhone());
        user.setGender(registrationDTO.getGender());
        user.setDob(registrationDTO.getDob());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setRole(registrationDTO.getRole());

        // Set advisor-specific fields if the user is an advisor
        if (registrationDTO.getRole().equals(Role.ADVISOR)) {
            AdvisorInfo advisorInfo = new AdvisorInfo();
            advisorInfo.setExpertise(registrationDTO.getExpertise());
            advisorInfo.setRating(registrationDTO.getRating());
            user.setAdvisorInfo(advisorInfo);
        }

        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    // Log in
    public AuthenticationResult validateUserCredentials(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            // User not found
            return AuthenticationResult.USER_NOT_FOUND;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            // Wrong password
            return AuthenticationResult.WRONG_PASSWORD;
        }

        //user.getEmail();

        // Success
        return AuthenticationResult.SUCCESS;
    }

    public enum AuthenticationResult {
        SUCCESS,
        USER_NOT_FOUND,
        WRONG_PASSWORD
    }
}