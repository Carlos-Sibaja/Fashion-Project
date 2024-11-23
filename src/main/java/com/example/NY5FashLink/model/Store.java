package com.example.NY5FashLink.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "stores")
    public class Store {
        @Setter
        @Getter
        @Id
        //private ObjectId id;
        private String id;

       @NotEmpty(message = "Store Name is required")
       @Field(value = "storeName")
       private String storeName;


       @NotEmpty(message = "Commission Rate is required")
       @Field(value = "comissionRate")
       private double comissionRate;


       @NotEmpty(message = "Campaign Code is required")
       @Field(value = "comissionCode")
       private String comissionCode;


       @NotEmpty(message = "Start Date of Publication is required")
       @Field(value = "startDate")
       private String startDate;

       @NotEmpty(message = "Ending Date of Publication is required")
       @Field(value = "endingdate")
       private String endingdate;

        @NotEmpty(message = "Add the Advertising Piece")
        @Field(value = "artPieceURL")
        private String artPieceURL;



    @NotEmpty(message = "Commission Rate is required")
    public double getComissionRate() {
        return comissionRate;
    }

    public void setComissionRate(@NotEmpty(message = "Commission Rate is required") double comissionRate) {
        this.comissionRate = comissionRate;
    }

    public @NotEmpty(message = "Campaign Code is required") String getComissionCode() {
        return comissionCode;
    }

    public void setComissionCode(@NotEmpty(message = "Campaign Code is required") String comissionCode) {
        this.comissionCode = comissionCode;
    }

    public @NotEmpty(message = "Start Date of Publication is required") String getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotEmpty(message = "Start Date of Publication is required") String startDate) {
        this.startDate = startDate;
    }

    public @NotEmpty(message = "Ending Date of Publication is required") String getEndingdate() {
        return endingdate;
    }

    public void setEndingdate(@NotEmpty(message = "Ending Date of Publication is required") String endingdate) {
        this.endingdate = endingdate;
    }

    public @NotEmpty(message = "Add the Advertising Piece") String getArtPieceURL() {
        return artPieceURL;
    }

    public void setArtPieceURL(@NotEmpty(message = "Add the Advertising Piece") String artPieceURL) {
        this.artPieceURL = artPieceURL;
    }

    public @NotEmpty(message = "Store Name is required") String getStoreName() {
        return storeName;
    }

    public void setStoreName(@NotEmpty(message = "Store Name is required") String storeName) {
        this.storeName = storeName;
    }

}



