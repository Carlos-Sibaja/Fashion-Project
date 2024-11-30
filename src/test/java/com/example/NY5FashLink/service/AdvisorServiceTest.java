package com.example.NY5FashLink.service;

import com.example.NY5FashLink.model.Advisor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvisorServiceTest {

    @Test
    void findAdvisors() {

    }

    @Test
    void findById() {
        AdvisorService advisorService = new AdvisorService();
        Advisor advisor = advisorService.findById("6734feb58735e7e22bac042c");
        System.out.println(advisor);
        assertEquals("6734feb58735e7e22bac042c", advisor.getId());
    }
}