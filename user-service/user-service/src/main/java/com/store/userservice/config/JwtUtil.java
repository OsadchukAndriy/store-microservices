package com.store.userservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Той самий ключ для фільтра

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Ім'я користувача
                .setIssuedAt(new Date(System.currentTimeMillis())) // Час створення
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Термін дії токена: 1 година
                .signWith(SECRET_KEY) // Секретний ключ
                .compact();
    }

    public SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}
