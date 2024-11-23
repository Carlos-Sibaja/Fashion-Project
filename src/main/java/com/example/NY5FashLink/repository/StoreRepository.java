package com.example.NY5FashLink.repository;

import com.example.NY5FashLink.model.Store;
import com.example.NY5FashLink.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StoreRepository extends MongoRepository<Store, String> {
    Optional<Users> findByEmail(String email);
    //Custom queries
}