package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.User;
import com.example.NY5FashLink.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign_up")
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "sign_up";
        }

        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("emailError", "Email already exists");
            return "sign_up";
        }

        userService.registerUser(user);
        return "redirect:/success";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


}
