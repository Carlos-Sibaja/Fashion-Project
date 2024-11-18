package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.Advisor;
import com.example.NY5FashLink.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/advisors")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    // Map root path /advisors to serve advisors.html
    @GetMapping
    public String viewAdvisors(Model model) {
        // Fetch all advisors
        List<Advisor> advisors = getFilteredAdvisors(null,null,null,null,null);

        // Add advisors to the model
        model.addAttribute("advisors", advisors);
        return "advisors";  // Return the advisors.html view
    }

    @GetMapping("/filter")
    public List<Advisor> getFilteredAdvisors(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String availability,
            @RequestParam(required = false) Double minCost,
            @RequestParam(required = false) Double maxCost,
            @RequestParam(required = false) String name) {

        return advisorService.findAdvisors(category, availability, minCost, maxCost, name);
    }

    @PostMapping("/filter")
    public String filterAdvisors(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String availability,
            @RequestParam(required = false) Double minCost,
            @RequestParam(required = false) Double maxCost,
            @RequestParam(required = false) String name,
            Model model) {

        category = category.toUpperCase();
        category = category.replace("_", " ");
        List<Advisor> advisors = advisorService.findAdvisors(category, availability, minCost, maxCost, name);

        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedAvailability", availability);
        model.addAttribute("selectedMinCost", minCost);
        model.addAttribute("selectedMaxCost", maxCost);
        model.addAttribute("selectedName", name);
        model.addAttribute("advisors", advisors);

        return "advisors"; // Return the view name to render the advisors page
    }

}

