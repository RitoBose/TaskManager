// JwtUtil.java
package com.intern.taskM.util;
import io.jsonwebtoken.*;

import org.springframework.stereotype.Component;

import java.security.Key;
import io.jsonwebtoken.security.Keys;
import com.intern.taskM.model.User;

import java.util.Date;

@Component
public class JwtUtil {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);  // Need to manage securely in real app
    private final long EXPIRATION_TIME = 86_400_000; // 24 hours in milliseconds

    public String generateToken(User user) {
        return Jwts.builder()
                   .setSubject(user.getUsername())
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                   .signWith( SECRET_KEY)
                   .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(SECRET_KEY)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
        	Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()  // Build the JwtParser
            .parseClaimsJws(token);
        return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println(e);
        }
        return false;
    }
}
