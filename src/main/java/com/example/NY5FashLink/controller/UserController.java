package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.*;
import com.example.NY5FashLink.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign_up")
    public String showSignUpPage(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String registerUser(@ModelAttribute UserRegistrationDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "sign_up";
        }

        if (userService.emailExists(dto.getEmail())) {
            model.addAttribute("emailError", "Email already exists");
            return "sign_up";
        }

        userService.registerUser(dto);
        return "redirect:/success";
    }
}
