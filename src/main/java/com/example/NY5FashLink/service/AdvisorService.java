package com.example.NY5FashLink.service;

import com.example.NY5FashLink.model.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdvisorService {

    @Autowired
    MongoTemplate mongoTemplate = null;

    public List<Advisor> findAdvisors(String category, String availability, Double minCost,
                                      Double maxCost, String name ) {

        // Create a query with dynamic filtering
        Query query = new Query();

        //Only advisors (other users do not have data in advisors subsection)
        query.addCriteria(Criteria.where("advisor").ne(null));

        // Filter by category if specified
        if (category != null && !category.isEmpty()) {
            query.addCriteria(Criteria.where("advisor.categories").in(Arrays.asList(category)));

        }

        // Filter by availability if specified
        if (availability != null && !availability.isEmpty()) {
            query.addCriteria(Criteria.where("advisor.availability").in(Arrays.asList(availability.toUpperCase())));
        }

        // Filter by consultation cost range if specified
        if (minCost != null && maxCost != null) {
            query.addCriteria(Criteria.where("advisor.consultationCost").gte(minCost).lte(maxCost));
        } else if (minCost != null) {
            query.addCriteria(Criteria.where("advisor.consultationCost").gte(minCost));
        } else if (maxCost != null) {
            query.addCriteria(Criteria.where("advisor.consultationCost").lte(maxCost));
        }

        // Filter by name if specified
        if (name != null && !name.isEmpty()) {
            // Divide full name in parts to combine first and last name
            String[] nameParts = name.split(" ");
            if (nameParts.length > 1) {
                String firstName = nameParts[0];
                String lastName = nameParts[1];

                query.addCriteria(
                        new Criteria().andOperator(
                                Criteria.where("firstName").regex("^" + firstName, "i"),
                                Criteria.where("lastName").regex("^" + lastName, "i")
                        )
                );
            } else {
                // Only one name, search by first name
                query.addCriteria(Criteria.where("firstName").regex("^" + name, "i"));
            }
        }

        System.out.println(name);

        System.out.println(query.toString());

        return mongoTemplate.find(query, Advisor.class);
    }

}

