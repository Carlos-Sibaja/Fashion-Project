package com.example.NY5FashLink.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "transactions")
@Data
public class Booking extends Transaction{
    @Field(value = "booking.customerEmail")
    private String customerEmail;
    @Field(value = "booking.bookingDate")
    private String bookingDate;
    @Field(value = "booking.bookingTime")
    private String bookingTime;
    @Field(value = "booking.bookingStatus")
    private String bookingStatus;
    @Field(value = "booking.advisorId")
    private String advisorId;
    @Field(value = "booking.consultationCost")
    private Double bookingConsultationCost;
    @Field(value = "booking.notes")
    private String notes;
    @Field(value = "booking.bookingUpdDateTime")
    private LocalDateTime bookingUpdDateTime;
    @Field(value = "feedback.ratingStars")
    private LocalDateTime feedbackRatingStars;
    @Field(value = "feedback.review")
    private List<String> feedbackReview;
    @Field(value = "feedback.message")
    private LocalDateTime feedbackMessage;

    // No-arg constructor
    public Booking() {}
}
