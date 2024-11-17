package com.example.NY5FashLink.repository;

import com.example.NY5FashLink.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, String> {
    Users findByEmail(String email);
}
