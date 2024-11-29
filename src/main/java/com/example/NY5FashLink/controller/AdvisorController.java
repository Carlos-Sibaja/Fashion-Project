package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.Advisor;
import com.example.NY5FashLink.model.BookingWithAdvisor;
import com.example.NY5FashLink.service.AdvisorService;
import com.example.NY5FashLink.service.BookingService;
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
    @Autowired
    private BookingService bookingService;

    public AdvisorController(AdvisorService advisorService, BookingService bookingService) {
        this.advisorService = advisorService;
        this.bookingService = bookingService;
    }

    // Map root path /advisors to serve advisors.html
    @GetMapping
    public String viewAdvisors(@SessionAttribute(value = "loggedInUser", required = false) String loggedInUser,
                               @SessionAttribute(value = "loggedInUserEmail", required = false) String loggedInUserEmail,
                               Model model) {
        // Fetch all advisors
        List<Advisor> advisors = getFilteredAdvisors(null,null,null,null,null);

        // Add advisors to the model
        model.addAttribute("advisors", advisors);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("loggedInUserEmail", loggedInUserEmail);
        return "advisors";  // Return the advisors.html view
    }

    // When you click on Advisors menu
    @GetMapping("/filter")
    public List<Advisor> getFilteredAdvisors(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String availability,
            @RequestParam(required = false) Double minCost,
            @RequestParam(required = false) Double maxCost,
            @RequestParam(required = false) String name) {

        return advisorService.findAdvisors(category, availability, minCost, maxCost, name);
    }

    // Filter advisor's list according to the filter
    @PostMapping("/filter")
    public String filterAdvisors(   @SessionAttribute(value = "loggedInUser", required = false) String loggedInUser,
                                    @SessionAttribute(value = "loggedInUserEmail", required = false) String loggedInUserEmail,
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

        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("loggedInUserEmail", loggedInUserEmail);

        return "advisors"; // Return the view name to render the advisors page
    }

    // Booking a new appointment - Advisor and Customer
    @GetMapping("/book/{advisorId}")
    public String createNewBooking( @PathVariable("advisorId") String advisorId,
                                    @SessionAttribute(value = "loggedInUser", required = false) String loggedInUser,
                                    @SessionAttribute(value = "loggedInUserEmail", required = false) String loggedInUserEmail,
                                    Model model) {

        System.out.println("Booking a new appointment - Advisor and Customer");
        System.out.println(advisorId);

        Advisor advisor = advisorService.findById(advisorId);
        String bookingStatus = "NEW";

        model.addAttribute("advisor", advisor);
        model.addAttribute("bookingStatus", bookingStatus);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("loggedInUserEmail", loggedInUserEmail);

        if (advisor != null) {
            System.out.println("Advisor found: " + advisor);
            System.out.println(bookingStatus);
        } else {
            System.out.println("No advisor found with ID: " + advisorId);
        }

        return "booking";
    }

    // Rescheduling an existing appointment - Advisor and Customer
    @GetMapping("/book/{advisorId}/{bookingId}")
    public String reschedulingBooking(  @PathVariable("advisorId") String advisorId,
                                        @PathVariable("bookingId") String bookingId,
                                        @SessionAttribute(value = "loggedInUser", required = false) String loggedInUser,
                                        @SessionAttribute(value = "loggedInUserEmail", required = false) String loggedInUserEmail,
                                        Model model) {

        System.out.println("Rescheduling an existing appointment - Advisor and Customer");
        System.out.println(advisorId);
        System.out.println(bookingId);

        Advisor advisor = advisorService.findById(advisorId);
        BookingWithAdvisor booking = bookingService.getSpecificBooking(bookingId);
        String bookingStatus = "RESCHEDULED";

        model.addAttribute("advisor", advisor);
        model.addAttribute("booking", booking);
        model.addAttribute("bookingStatus", bookingStatus);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("loggedInUserEmail", loggedInUserEmail);

        if (advisor != null) {
            System.out.println("Advisor found: " + advisor);
        } else {
            System.out.println("No advisor found with ID: " + advisorId);
        }

        if (booking != null) {
            System.out.println("Booking found: " + booking);
        } else {
            System.out.println("No booking found with ID: " + bookingId);
        }

        return "booking";
    }



}

