package com.example.NY5FashLink.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.Advisor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
public class Booking extends Transaction{
    @Field(value = "booking.bookingID")
    private int bookingId;
    @Field(value = "booking.customer")
    private Customer customer;
    @Field(value = "booking.bookingDate")
    private String bookingDate;
    @Field(value = "booking.bookingTime")
    private String bookingTime;
    @Field(value = "booking.bookingStatus")
    private String bookingStatus;
    @Field(value = "booking.advisor")
    private Advisor advisor;
    @Field(value = "booking.notes")
    private String notes;
}
