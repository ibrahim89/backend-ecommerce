package com.ecommerce.user_service.security.validate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class TokenValidate {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public boolean validateToken(String token) {
        if (SECRET_KEY == null || SECRET_KEY.isEmpty())
            throw new IllegalArgumentException("Not found secret key in structure");

        if (token.startsWith("Bearer "))
            token = token.replace("Bearer ", "");

        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            // handle ExpiredJwtException, SecurityException, etc.
            return false;
        }
    }
}
