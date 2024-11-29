package com.example.NY5FashLink.service;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsultationService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void cancelBooking(String bookingId) {

        // Convert the string bookingId to an ObjectId
        ObjectId objectId = new ObjectId(bookingId);

        // Filter by BookingID
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(objectId));

        // Define the fields which will be updated
        Update update = new Update()
                .set("booking.bookingStatus", "CANCELLED")
                .set("booking.bookingUpdDateTime", LocalDateTime.now())
                .set("booking.bookingNotes", "Consultation booking with ID " + bookingId + " was cancelled by the customer.");

        // Apply the change
        mongoTemplate.updateFirst(query, update, "transactions");

        System.out.println(query);

        System.out.println("Consultation booking with ID " + bookingId + " was cancelled by the customer.");
    }

    public void rescheduleBooking(String bookingId, String rescheduleDate, String rescheduleTime) {

        // Convert the string bookingId to an ObjectId
        ObjectId objectId = new ObjectId(bookingId);

        // Filter by BookingID
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(objectId));

        System.out.println("Reschedule to: " + rescheduleDate + " " + rescheduleTime);

        // Define the fields which will be updated
        Update update = new Update()
                .set("booking.bookingDate", rescheduleDate)
                .set("booking.bookingTime", rescheduleTime)
                .set("booking.bookingUpdDateTime", LocalDateTime.now())
                .set("booking.bookingNotes", "Consultation booking with ID " + bookingId + " was rescheduled by the customer.");;

        // Apply the change
        mongoTemplate.updateFirst(query, update, "transactions");

        System.out.println("Consultation booking with ID " + bookingId + " was rescheduled by the customer.");
    }

    public void completeBooking(String bookingId) {

        // Convert the string bookingId to an ObjectId
        ObjectId objectId = new ObjectId(bookingId);

        // Filter by BookingID
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(objectId));

        // Define the fields which will be updated
        Update update = new Update()
                .set("booking.bookingStatus", "COMPLETED")
                .set("booking.bookingUpdDateTime", LocalDateTime.now())
                .set("booking.bookingNotes", "Consultation booking with ID " + bookingId + " has been successfully completed.");

        // Apply the change
        mongoTemplate.updateFirst(query, update, "transactions");

        System.out.println("Consultation booking with ID " + bookingId + " has been successfully completed.");
    }

    public void shareFeedback(String bookingId, int numStars, List<String> review, String message) {

        // Convert the string bookingId to an ObjectId
        ObjectId objectId = new ObjectId(bookingId);

        // Filter by BookingID
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(objectId));

        // Define the fields which will be updated
        Update update = new Update()
                .set("feedback.ratingStars", numStars)
                .set("feedback.review", review)
                .set("feedback.message", message);

        // Apply the change
        mongoTemplate.updateFirst(query, update, "transactions");

        System.out.println("Feedback for booking ID " + bookingId + " has been successfully included.");
    }
}
