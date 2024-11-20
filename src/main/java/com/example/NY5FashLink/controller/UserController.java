package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.*;
import com.example.NY5FashLink.service.CloudinaryService;
import com.example.NY5FashLink.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    //private final CloudinaryService cloudinaryService;

    // Define a constant for the maximum file size (in bytes)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB

    @GetMapping("/sign_up")
    public String showSignUpPage(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String registerUser(@ModelAttribute UserRegistrationDTO dto,
                               @RequestParam("profilePicture") MultipartFile profilePicture,
                               BindingResult result,
                               Model model) {
        try {
            // Check for validation errors
            if (result.hasErrors()) {
                System.out.println("Validation errors: " + result.getAllErrors());
                return "sign_up";
            }

            // Check if email already exists
            if (userService.emailExists(dto.getEmail())) {
                System.out.println("Email already exists: " + dto.getEmail());
                model.addAttribute("emailError", "Email already exists");
                return "sign_up";
            }

            // Validate the file (if provided)
            if (profilePicture != null && !profilePicture.isEmpty()) {
                if (profilePicture.getSize() > MAX_FILE_SIZE) {
                    System.out.println("File size exceeds the limit for: " + profilePicture.getOriginalFilename());
                    model.addAttribute("error", "File size exceeds the limit");
                    return "sign_up";
                }

                String contentType = profilePicture.getContentType();
                if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
                    System.out.println("Unsupported file type: " + contentType);
                    model.addAttribute("error", "Unsupported file type. Please upload JPEG or PNG.");
                    return "sign_up";
                }
            }

            // Register the user with the profile picture
            userService.registerUser(dto, profilePicture);
            System.out.println("User registered successfully: " + dto.getEmail());
            return "redirect:/success";

        } catch (IOException e) {
            System.err.println("Error processing user registration: " + e.getMessage());
            model.addAttribute("error", "Error uploading profile picture");
            return "sign_up";
        }
    }
}