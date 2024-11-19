package com.example.NY5FashLink.service;

import com.example.NY5FashLink.model.*;
import com.example.NY5FashLink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Sign Up
    public void registerUser(UserRegistrationDTO registrationDTO) {
        Users users = new Users();

        // Set common fields
        users.setFirstName(registrationDTO.getFirstName());
        users.setLastName(registrationDTO.getLastName());
        users.setEmail(registrationDTO.getEmail());
        users.setPhone(registrationDTO.getPhone());
        users.setGender(registrationDTO.getGender());
        users.setDob(registrationDTO.getDob());
        users.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        users.setRole(registrationDTO.getRole());

        // Set advisor-specific fields if the user is an advisor
        if (registrationDTO.getRole().equals(Role.ADVISOR)) {
            AdvisorInfo advisorInfo = new AdvisorInfo();
            advisorInfo.setExpertise(registrationDTO.getExpertise());
            advisorInfo.setRating(registrationDTO.getRating());
            users.setAdvisorInfo(advisorInfo);
        }

        userRepository.save(users);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Get logged-in user
    public String getLoggedInUserFirstName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Check if the user is authenticated via OAuth2
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                // Access user's first name (you can modify the attribute based on the OAuth2 provider)
                return oauth2User.getAttribute("given_name");
            }
            // Check if the user is authenticated via form login
            else if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
                return authentication.getName(); // Returns the email
            }
        }
        return null; // No authenticated user
    }
}