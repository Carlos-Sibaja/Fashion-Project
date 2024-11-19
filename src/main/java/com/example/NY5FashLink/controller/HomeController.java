package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        String loggedInUser = userService.getLoggedInUserFirstName();

        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        } else {
            model.addAttribute("loggedInUser", null);
        }

        return "index";
    }
}
