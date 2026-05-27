package com.irenejoy15.springboot.todos.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irenejoy15.springboot.todos.request.RegisterRequest;
import com.irenejoy15.springboot.todos.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

// A.1 - Create a new class named AuthenticationController in the com.irenejoy15.springboot.todos.controller package. This class will serve as the REST controller for handling authentication-related endpoints, such as user registration and login.
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthenticationController {
    // A.2 - Create a private final field of type AuthenticationService in the AuthenticationController class. This will be used to call the authentication service methods for handling user registration and login logic.
    private final AuthenticationService authenticationService;

    // A.3 - Create a constructor for the AuthenticationController class that takes an AuthenticationService as a parameter and assigns it to the authenticationService field. This allows Spring to inject the AuthenticationService implementation when creating an instance of AuthenticationController.
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // A.4 - Create a method named register in the AuthenticationController class that takes a RegisterRequest object as a parameter. This method will be responsible for handling user registration requests. It should call the register method of the AuthenticationService to perform the registration logic and return an appropriate response.
    @Operation(summary = "Register a new user", description = "Endpoint for user registration")
    @ResponseStatus(HttpStatus.CREATED)    
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }
}
