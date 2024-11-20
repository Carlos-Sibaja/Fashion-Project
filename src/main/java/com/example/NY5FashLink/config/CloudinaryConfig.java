package com.example.NY5FashLink.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dyswu3ilm",
                "api_key", "199434423456919",
                "api_secret", "IwEgTJyp84Ly3APSZmG7eIFQO_A"));
    }
}