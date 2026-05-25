package com.irenejoy15.springboot.todos.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

// A.1 - Create a JwtService interface that defines methods for extracting the username from a JWT, validating the token, and generating a new token.
public interface JwtService {
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
