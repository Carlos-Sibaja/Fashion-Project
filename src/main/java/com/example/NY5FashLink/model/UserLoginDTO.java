package com.example.NY5FashLink.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDTO {

    @NotEmpty(message = "Email is Required")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;

}
