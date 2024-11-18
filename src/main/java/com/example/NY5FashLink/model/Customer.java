package com.example.NY5FashLink.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class Customer extends Users {

    private List<Booking> bookings;


}
