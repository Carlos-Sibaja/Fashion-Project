package com.example.NY5FashLink.model;

import lombok.Data;

import java.util.List;

@Data
public class BookingWithAdvisor {
    private String id;
    private String advisorId;
    private String customerEmail;

    private String firstName;
    private String lastName;

    private String date;
    private String time;
    private String status;
    private String formattedDateTime;
    private double consultationCost;

    private int numberReviews;
    private int activeClients;
    private int numberConsultations;
    private double rating;
    private String urlPicture;
    private String profile;
    private List<String> categories;
    private List<String> availability;

}
