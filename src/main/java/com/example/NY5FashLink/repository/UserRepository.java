package com.example.NY5FashLink.repository;

import com.example.NY5FashLink.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
