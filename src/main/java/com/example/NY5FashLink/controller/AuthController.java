package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.UserLoginDTO;
import com.example.NY5FashLink.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("userLogin", new UserLoginDTO());

        if (error != null) {
            switch (error) {
                case "user-not-found":
                    model.addAttribute("loginError", "User not found. Please check your email.");
                    break;
                case "wrong-password":
                    model.addAttribute("loginError", "Incorrect password. Please try again.");
                    break;
                default:
                    model.addAttribute("loginError", "Invalid username or password.");
                    break;
            }
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userLogin") UserLoginDTO loginDTO, Model model, HttpSession session) {
        UserService.AuthenticationResult result = userService.validateUserCredentials(loginDTO.getEmail(), loginDTO.getPassword());

        switch (result) {
            case SUCCESS -> {
                // User authenticated successfully
                session.setAttribute("loggedInUser", loginDTO.getEmail());
                return "redirect:/"; // Redirect to the home page or intended page
            }
            case USER_NOT_FOUND -> {
                // User not found, show appropriate error message
                return "redirect:/login?error=user-not-found"; // Redirect with the user-not-found error
            }
            case WRONG_PASSWORD -> {
                // Incorrect password, show appropriate error message
                return "redirect:/login?error=wrong-password"; // Redirect with the wrong-password error
            }
            default -> {
                return "redirect:/login?error"; // Redirect with a generic error
            }
        }
    }
}
