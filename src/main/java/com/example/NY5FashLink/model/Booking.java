package com.example.NY5FashLink.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

    // No-arg constructor
    public Booking() {}
}
