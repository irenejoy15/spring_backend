package com.irenejoy15.springboot.todos.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

// A.2 - Implement the JwtService interface in a class called JwtServiceImpl. For now, you can leave the method implementations empty or return default values.
@Service
public class JwtServiceImpl implements JwtService {

    // A.3 - Use the @Value annotation to inject the secret key and expiration time for the JWT from the application.properties file.
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Value("${spring.jwt.expiration}")
    private long EXPIRATION;
    // END A.3
    @Override
    public String extractUsername(String token) {
        return null;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return null;
    }

}
