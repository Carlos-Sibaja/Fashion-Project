package com.example.NY5FashLink.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String phone;
    @NotEmpty(message = "Password is required")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotEmpty(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password is required") String password) {
        this.password = password;
    }
}
