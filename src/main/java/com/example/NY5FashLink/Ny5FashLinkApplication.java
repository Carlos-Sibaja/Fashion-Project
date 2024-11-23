package com.example.NY5FashLink;

import com.example.NY5FashLink.repository.AdvisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
public class Ny5FashLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ny5FashLinkApplication.class, args);
	}

}


