package com.irenejoy15.springboot.todos.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


// A.2 - Implement the JwtService interface in a class called JwtServiceImpl. For now, you can leave the method implementations empty or return default values.
@Service
public class JwtServiceImpl implements JwtService {

    // A.3 - Use the @Value annotation to inject the secret key and expiration time for the JWT from the application.properties file.
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Value("${spring.jwt.expiration}")
    private long JWT_EXPIRATION;
    // END A.3
    @Override
    public String extractUsername(String token) {
        // C.3 - edit the return statement to call the extractClaim method, passing the token and a lambda function that retrieves the subject claim from the Claims object.
        return extractClaim(token, Claims::getSubject);
    }
    // C.2 - Create a private method extractClaim that takes a JWT and a Function to extract a specific claim from the token. This method will be used by extractUsername to get the subject claim.
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // C.1 - Implement the extractUsername method to parse the JWT and extract the username (subject) from the token claims.
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // D.3 Edit return statement to check if the username extracted from the token matches the username in the UserDetails object and if the token has not expired by calling the isTokenExpired method.
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    // D.2 - Create a private method isTokenExpired that checks if the token has expired by comparing the expiration date from the token claims with the current date.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // D.1 - Implement the isTokenValid method to check if the username extracted from the token matches the username in the UserDetails object and if the token has not expired.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        // B.1 - Implement the generateToken method to create a JWT using the Jwts.builder() method. Set the claims, subject (username), issued date, expiration date, and sign the token with the secret key.
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }
    
    // B.2 Create a private method getSigningKey() that decodes the secret key from Base64 and returns a SecretKey object.
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
