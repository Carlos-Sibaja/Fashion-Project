package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.UserRegistrationDTO;
import com.example.NY5FashLink.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Controller
@RequestMapping("/admin/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", new UserRegistrationDTO());
        return "customers";
    }

    @PostMapping
    public String registerUser(@ModelAttribute UserRegistrationDTO dto,
                               @RequestParam("profilePicture") MultipartFile profilePicture,
                               BindingResult result,
                               Model model) {
        try {
            // Check for validation errors
            if (result.hasErrors()) {
                System.out.println("Validation errors: " + result.getAllErrors());
                return "redirect:/admin/customers";
            }

            // Check if email already exists
            if (customerService.emailExists(dto.getEmail())) {
                System.out.println("Email already exists: " + dto.getEmail());
                model.addAttribute("emailError", "Email already exists");
                return "redirect:/admin/customers";
            }

            // Validate the file (if provided)
            if (profilePicture != null && !profilePicture.isEmpty()) {
//                if (profilePicture.getSize() > MAX_FILE_SIZE) {
//                    System.out.println("File size exceeds the limit for: " + profilePicture.getOriginalFilename());
//                    model.addAttribute("error", "File size exceeds the limit");
//                    return "sign_up";
//                }

                String contentType = profilePicture.getContentType();
                if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
                    System.out.println("Unsupported file type: " + contentType);
                    model.addAttribute("error", "Unsupported file type. Please upload JPEG or PNG.");
                    return "redirect:/admin/customers";
                }
            }

            // Register the user with the profile picture
            customerService.registerUser(dto, profilePicture);
            System.out.println("User registered successfully: " + dto.getEmail());
            return "redirect:/admin";

        } catch (IOException e) {
            System.err.println("Error processing user registration: " + e.getMessage());
            model.addAttribute("error", "Error uploading profile picture");
            return "redirect:/admin/customers";
        }
    }

//    @GetMapping("/edit/{id}")
//    public String editCustomer(@PathVariable String id, Model model) {
//        model.addAttribute("customer", customerService.findById(id));
//        return "admin/edit_customer"; // A separate edit page
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateCustomer(@PathVariable String id, @ModelAttribute CustomerDTO customer) {
//        customerService.updateCustomer(id, customer);
//        return "redirect:/admin/customers";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteCustomer(@PathVariable String id) {
//        customerService.deleteCustomer(id);
//        return "redirect:/admin/customers";
//    }
}