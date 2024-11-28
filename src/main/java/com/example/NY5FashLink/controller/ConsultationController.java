package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.service.ConsultationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    ConsultationService consultationService = new ConsultationService();

    public ConsultationController() {
        this.consultationService = new ConsultationService();
    }

    @PostMapping
    public String handleConsultationForm(@SessionAttribute(value = "loggedInUser", required = false) String loggedInUser,
                                         @SessionAttribute(value = "loggedInUserEmail", required = false) String loggedInUserEmail,
                                         @RequestParam("booking_id") String bookingId,
                                         @RequestParam("advisor_name") String advisorName,
                                         Model model) {

        System.out.println("Consultation request received");

        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("loggedInUserEmail", loggedInUserEmail);
        model.addAttribute("bookingId", bookingId);
        model.addAttribute("advisorName", advisorName);

        return "consultation";
    }
}
