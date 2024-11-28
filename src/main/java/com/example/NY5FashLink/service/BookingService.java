package com.example.NY5FashLink.service;

import com.example.NY5FashLink.model.Booking;
import com.example.NY5FashLink.model.BookingWithAdvisor;
import com.example.NY5FashLink.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ObjectOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final MongoTemplate mongoTemplate;
    private final BookingRepository bookingRepository;

    public List<BookingWithAdvisor> getBookingsWithAdvisors(String loggedInUserEmail) {
        System.out.println(loggedInUserEmail);

        Aggregation aggregation = Aggregation.newAggregation(
                // Filter transactions by customer email
                Aggregation.match(Criteria.where("booking.customerEmail").is(loggedInUserEmail)),

                // Convert advisorId from String to ObjectId using $toObjectId operator directly
                Aggregation.addFields()
                        .addField("advisorIdAsObjectId").withValue(
                                new Document("$toObjectId", "$booking.advisorId")
                        )
                        .build(),

                // Create a combined datetime field
                Aggregation.addFields()
                        .addField("bookingDateTime").withValue(
                                new Document("$dateFromString",
                                        new Document("dateString",
                                                new Document("$concat", Arrays.asList("$booking.bookingDate", "T", "$booking.bookingTime"))
                                        )
                                )
                        )
                        .build(),

                // Perform a lookup to join with the 'users' collection
                Aggregation.lookup("users", "advisorIdAsObjectId", "_id", "advisorDetails"),

                // Unwind the advisorDetails array
                Aggregation.unwind("advisorDetails", true),

                // Sort by the combined datetime field
                Aggregation.sort(Sort.by(Sort.Direction.ASC, "bookingDateTime")),

                // Project the required fields
                Aggregation.project()
                        .and("booking._id").as("id")
                        .and("booking.customerEmail").as("customerEmail")
                        .and("advisorDetails._id").as("advisorId")
                        .and("advisorDetails.name").as("firstName")
                        .and("advisorDetails.lastname").as("lastName")
                        .and("booking.bookingDate").as("date")
                        .and("booking.bookingTime").as("time")
                        .and("booking.consultationCost").as("consultationCost")
                        .and("booking.bookingStatus").as("bookingStatus")
                        .and("advisorDetails.advisor.urlPicture").as("urlPicture")
                        .and("advisorDetails.advisor.categories").as("categories")
                        .and("advisorDetails.advisor.numberReviews").as("numberReviews")
                        .and("advisorDetails.advisor.activeClients").as("activeClients")
                        .and("advisorDetails.advisor.numberConsultations").as("numberConsultations")
                        .and("advisorDetails.advisor.rating").as("rating")
                        .and("advisorDetails.advisor.profile").as("profile")
                        .and("advisorDetails.advisor.availability").as("availability")
        );

        // Execute the aggregation query
        List<BookingWithAdvisor> results = mongoTemplate.aggregate(aggregation, "transactions", BookingWithAdvisor.class).getMappedResults();

        // Print the results to check
        System.out.println("Aggregation Results: " + results);

        return results;
    }

    public void saveBooking(String advisorId, String customerEmail, String date, String time, Double consultationCost) {
        // Create a new booking object
        Booking booking = new Booking();
        booking.setDate(new java.util.Date());
        booking.setService("Single Consultation");
        booking.setAdvisorId(advisorId);
        booking.setCustomerEmail(customerEmail);
        booking.setBookingDate(date);
        booking.setBookingTime(time);
        booking.setFee(consultationCost);
        booking.setBookingConsultationCost(consultationCost);
        booking.setTotal(consultationCost);
        booking.setBookingStatus("NEW");
        System.out.println(booking);

        // Save the booking to the database
        Booking savedBooking = bookingRepository.save(booking);

        // Log the saved booking for verification
        System.out.println("Booking saved: " + savedBooking);
    }
}
