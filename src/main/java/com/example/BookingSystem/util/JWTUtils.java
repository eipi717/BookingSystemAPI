package com.example.BookingSystem.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
    @Value("${secret_key}")
    private String secret;

    private static final long expirationTime = 1000 * 60 * 60; // 1 hour
    private static final String tokenPrefix = "Bearer ";

    public String generateToken(String subject) {
        long currentTime = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return tokenPrefix + token;
    }

    public final String getUserName(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public final Boolean isExpired(String token) {
        try {
            return
                    Jwts.parser()
                            .setSigningKey(secret)
                            .parseClaimsJws(token)
                            .getBody()
                            .getExpiration().before(new Date());
        }
        catch (Exception e) {
            return true;
        }
    }

    public Boolean validateToken(String token, String username) {
        return (getUserName(token).equals(username) && !isExpired(token));
    }



}
