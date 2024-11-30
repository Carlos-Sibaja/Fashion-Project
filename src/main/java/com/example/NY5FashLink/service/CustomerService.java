package com.example.NY5FashLink.service;

import com.cloudinary.Cloudinary;
import com.example.NY5FashLink.model.*;
import com.example.NY5FashLink.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomerService {

    private final UserRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private Cloudinary cloudinary;


    public String uploadProfilePicture(MultipartFile file) throws IOException {
        // Convert the MultipartFile to a byte array
        byte[] fileBytes = file.getBytes();

        // Add required metadata
        Map<String, Object> uploadOptions = new HashMap<>();
        uploadOptions.put("resource_type", "image");

        // Upload file to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadOptions);

        // Extract and return the uploaded file's URL
        return (String) uploadResult.get("secure_url");
    }

    public void registerUser(UserRegistrationDTO registrationDTO, MultipartFile profilePicture) throws IOException {
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

        try {
            // Upload profile picture and get URL
            String pictureUrl = uploadProfilePicture(profilePicture);
            users.setProfilePictureURL(pictureUrl);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload profile picture", e);
        }


        customerRepository.save(users);
    }

    public boolean emailExists(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    // Get logged-in user
    public String getLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Check if the user is authenticated via OAuth2
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                return oauth2User.getAttribute("email");
            }
            // Check if the user is authenticated via form login
            else if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
                return authentication.getName(); // Returns user email
            }
        }
        return null;
    }
}
