package com.example.NY5FashLink.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private Date date;
    @Field(value = "card_id")
    private String cardID;
    private String service;
    private double fee;
    @Field(value = "transaction_code")
    private String transactionCode;
    private double total;
    @Field(value = "transaction_paid")
    private boolean transactionPaid;
    private String advisor;
    @Field(value = "comission_fee")
    private double comissionFee;
    @Field(value = "comission_total")
    private double comissionTotal;
    @Field(value = "comission_status")
    private String comissionStatus;
}
