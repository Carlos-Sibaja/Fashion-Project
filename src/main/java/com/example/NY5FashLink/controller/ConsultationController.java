package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.service.ConsultationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
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

    @PostMapping("/cancel")
    public String cancelConsultationForm(@RequestParam("booking_id") String bookingId) {

        System.out.println("Cancel consultation request received");

        consultationService.cancelBooking(bookingId);

        return "redirect:/booking/my_profile";
    }

    @PostMapping("/reschedule")
    public String rescheduleConsultationForm(@RequestParam("booking_id") String bookingId,
                                             @RequestParam("booking_date") String bookingDate,
                                             @RequestParam("booking_time") String bookingTime) {

        System.out.println("Reschedule consultation request received");

        consultationService.rescheduleBooking(bookingId, bookingDate, bookingTime);

        return "redirect:/booking/my_profile";
    }

    @GetMapping("/complete/{bookingId}")
    public String completeConsultationForm(@PathVariable("bookingId") String bookingId) {

        System.out.println("Complete consultation request received");

        consultationService.completeBooking(bookingId);

        return "feedback";
    }

    @PostMapping("feedback")
    public String shareFeedbackConsultationForm(@RequestParam("booking_id") String bookingId,
                                                @RequestParam("booking_ratingStars") int numStars,
                                                @RequestParam("booking_review") List<String> review,
                                                @RequestParam("booking_message") String message) {

        System.out.println("Share feedback for consultation request received");

        consultationService.shareFeedback(bookingId, numStars, review, message);

        return "redirect:/booking/my_profile";
    }
}
