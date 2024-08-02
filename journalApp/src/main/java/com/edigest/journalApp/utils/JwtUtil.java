package com.edigest.journalApp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Component
public class JwtUtil {

    private String SECRET_KEY="TaL+HaV^uvCHEFdEVfypW#7g9^k*Z8$V";
    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", username);
        return createToken(claims,username);
    }
    public String createToken(Map<String,Object> claims,String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*50))
                .signWith(getSigningKey())
                .compact();

    }
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token).getPayload();
    }
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
    public boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }

    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
/*Token Generation:

    generateToken and createToken methods create a JWT with claims and a subject.
    createToken sets claims, subject, header parameters, issue date, expiration date, and signing key.

Token Parsing and Validation:

    extractUsername extracts the username from the token.
    extractAllClaims parses the token to get all claims.
    isTokenExpired checks if the token is expired.
    validateToken validates the token (currently only checking if not expired).*/