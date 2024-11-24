package com.example.NY5FashLink.controller;

import com.example.NY5FashLink.model.BookingWithAdvisor;
import com.example.NY5FashLink.service.BookingService;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        // Define the date and time formatter
//        DateTimeFormatter dateTimeFormatterYMD = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        DateTimeFormatter dateTimeFormatterDMY = DateTimeFormatter.ofPattern("EEE., MMM dd yyyy hh:mm a");
//
//
//        // Loop through bookings and combine date and time
//        for (BookingWithAdvisor booking : bookings) {
//            String combinedDateTime = booking.getDate() + " " + booking.getTime();  // Combine date and time
//            LocalDateTime formattedDateTimeYMD = LocalDateTime.parse(combinedDateTime, dateTimeFormatterYMD);  // Convert to LocalDateTime
//            LocalDateTime formattedDateTimeDMY = LocalDateTime.parse(formattedDateTimeYMD.toString(), dateTimeFormatterDMY);  // Convert to LocalDateTime
//            //booking.setFormattedDateTime(formattedDateTime.toString());  // Set the formatted date and time
//            System.out.println(formattedDateTimeDMY);
//        }

        // Add data to the model
        model.addAttribute("bookings", bookings.get(0));
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("loggedInUserEmail", loggedInUserEmail);

        return "my_profile";
    }
}
