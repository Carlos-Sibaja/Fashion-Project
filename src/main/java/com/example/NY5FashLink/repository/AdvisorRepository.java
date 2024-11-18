package com.example.NY5FashLink.repository;

import com.example.NY5FashLink.model.Advisor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisorRepository extends MongoRepository<Advisor, String> {

    //Custom queries

}
