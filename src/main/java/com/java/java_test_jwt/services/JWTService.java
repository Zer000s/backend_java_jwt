package com.java.java_test_jwt.services;

import com.java.java_test_jwt.dtos.UserDTO;
import com.java.java_test_jwt.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private static final String SECRET_KEY = "X/mI85Z+Dzd3dKSdNbXnUGjFYN5D7GhLM4KV/YSKKEg=";

    // Создание JWT токена
    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // 1 час
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Проверка JWT токена
    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Извлечение имени пользователя из токена
    public String extractUsername(String token) {
        return validateToken(token).getSubject();
    }
}