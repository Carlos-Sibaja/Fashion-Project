package com.example.NY5FashLink.repository;

import com.example.NY5FashLink.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    //Custom queries
}
