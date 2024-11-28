package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    @GetMapping
    public String home(Model model) {
        String loggedInUser = userService.getLoggedInUserFirstName();
        String loggedInUserEmail = userService.getLoggedInUserEmail();

        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("loggedInUserEmail", loggedInUserEmail);
        } else {
            model.addAttribute("loggedInUser", null);
            model.addAttribute("loggedInUserEmail", null);
        }

        return "admin";
    }
}