package com.trelloplus.utils;

import com.trelloplus.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * What This Class Does:
 * Method	Description
 * generateToken()	Creates a JWT with subject and expiration
 * extractUsername()	Gets username from token
 * isTokenValid()	Verifies token matches user and not expired
 * parseToken()	Parses and verifies token signature using secret key
 */

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    //generate token
    public String generateToken(User user) {
        return Jwts.builder().setSubject(user.getUsername()).claim("role", user.getRole().name()) // Optional
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expirationMs)).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    //extract username based on token
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    //validate token
    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    //checking for token expiry which is set to 1 day as of now.
    private boolean isTokenExpired(String token) {
        return parseToken(token).getBody().getExpiration().before(new Date());
    }

    //parse provided token authorization
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
