package com.ecommerce.user_service.security.validate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import java.util.List;

public class AuthorityTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
    public List<String> checkPermission(String token) {
        Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))   // ✅ This is correct in 0.12.5
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();                // ✅ This replaces getBody()
        String username = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);
        return roles;
        }

}
