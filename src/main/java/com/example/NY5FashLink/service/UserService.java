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
        return userRepository.findByEmail(email) != null;
    }
}