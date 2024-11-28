package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.BookingWithAdvisor;
import com.example.NY5FashLink.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public String createBooking(@SessionAttribute(value = "loggedInUser", required = false) String loggedInUser,
                                @SessionAttribute(value = "loggedInUserEmail", required = false) String loggedInUserEmail,
                                @RequestParam("id") String advisorId,
                                @RequestParam("date") String date,
                                @RequestParam("time") String time,
                                @RequestParam("consultationCost") Double consultationCost) {

        // Call the service to save the booking
        bookingService.saveBooking(advisorId, loggedInUserEmail, date, time, consultationCost);

        // Redirect to the findBooking endpoint
        return "redirect:/booking/my_profile";
    }

    @GetMapping("/my_profile")
    public String findBookings(@SessionAttribute(value = "loggedInUser", required = false) String loggedInUser,
                               @SessionAttribute(value = "loggedInUserEmail", required = false) String loggedInUserEmail,
                               Model model) {

        // Retrieve bookings related to the logged-in user
        List<BookingWithAdvisor> bookings = bookingService.getBookingsWithAdvisors(loggedInUserEmail);

        // Define the original date-time format (yyyy-MM-dd HH:mm)
        DateTimeFormatter dateTimeFormatterYMD = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // Define the target format (For example: Sun., Nov. 24, 2024 - 12:00pm)
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("EEE, MMM. dd yyyy - h:mm a");

        // Loop through bookings and combine date and time
        for (BookingWithAdvisor booking : bookings) {
            // Combine date and time into a single string
            String combinedDateTime = booking.getDate() + " " + booking.getTime();

            // Parse the combined date-time string into a LocalDateTime object
            LocalDateTime formattedDateTimeYMD = LocalDateTime.parse(combinedDateTime, dateTimeFormatterYMD);

            // Format the LocalDateTime object into the desired output format
            String formattedDateTime = formattedDateTimeYMD.format(targetFormatter);

            // Set the formatted date-time to the booking object
            booking.setFormattedDateTime(formattedDateTime);
        }

        // Add data to the model
        System.out.println(bookings);
        model.addAttribute("bookings", bookings);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("loggedInUserEmail", loggedInUserEmail);

        return "my_profile";
    }
}
