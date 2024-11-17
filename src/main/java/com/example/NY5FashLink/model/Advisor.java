package com.example.NY5FashLink.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "users")
public class Advisor extends Users {

    @Field(value = "advisor.numberReviews")
    private int numberReviews;
    @Field(value = "advisor.activeClients")
    private int activeClients;
    @Field(value = "advisor.numberConsultations")
    private int numberConsultations;
    @Field(value = "advisor.rating")
    private double rating;
    @Field(value = "advisor.consultationCost")
    private double consultationCost;
    @Field(value = "advisor.urlPicture")
    private String urlPicture;
    @Field(value = "advisor.profile")
    private String profile;
    @Field(value = "advisor.categories")
    private List<String> categories;
    @Field(value = "advisor.availability")
    private List<String> availability;

}
