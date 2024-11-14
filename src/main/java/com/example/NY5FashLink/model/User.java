package com.example.NY5FashLink.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {
    @Setter
    @Getter
    @Id
    private String id;
    @NotEmpty(message = "First name is required")
    @Field(value = "name")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Field(value = "lastname")
    private String lastName;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @Setter
    @Getter
    private String phone;
    @Setter
    @Getter
    private String gender;
    @Setter
    @Getter
    private String dob;
    @NotEmpty(message = "Password is required")
    private String password;
    @Setter
    @Getter
    private Role role;
    @Setter
    @Getter
    private AdvisorInfo advisorInfo;

    public @NotEmpty(message = "First name is required") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty(message = "First name is required") String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty(message = "Last name is required") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "Last name is required") String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password is required") String password) {
        this.password = password;
    }

}
