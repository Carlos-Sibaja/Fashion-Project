package com.example.NY5FashLink.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.aop.Advisor;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
public class Booking {
    private int bookingId;
    private Customer customer;
    private String bookingDate;
    private String bookingTime;
    private String bookingStatus;
    private Advisor advisor;
    private String notes;
}
