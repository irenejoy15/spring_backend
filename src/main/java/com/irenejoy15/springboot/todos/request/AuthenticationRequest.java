package com.irenejoy15.springboot.todos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters long")
    private String password;
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
