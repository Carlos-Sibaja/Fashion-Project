package com.example.NY5FashLink.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UserRegistrationDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureURL;
    private MultipartFile profilePicture;
    private String phone;
    private String gender;
    private String dob;
    private String password;
    private Role role;
    private String expertise;
    private double rating = 0.0;
}
