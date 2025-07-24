package com.java.java_test_jwt.controllers;

import com.java.java_test_jwt.models.Token;
import com.java.java_test_jwt.models.User;
import com.java.java_test_jwt.repository.TokenRepository;
import com.java.java_test_jwt.repository.UserRepository;
import com.java.java_test_jwt.services.JWTService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/registration")
    public ResponseEntity<Object> register(@Valid @RequestBody User user) {
        try {
            Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity.badRequest().body("Username is already taken");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            String token = jwtService.generateToken(user.getUsername());
            userRepository.save(user);
            String cookie = ResponseCookie.from("jwt", token)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(Duration.ofDays(1))
                    .build()
                    .toString();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie).body(user);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        try {
            Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser.isEmpty() || !passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
            String token = jwtService.generateToken(user.getUsername());
            String cookie = ResponseCookie.from("jwt", token)
                    .httpOnly(true)
                    .path("/")
                    .maxAge(Duration.ofDays(1))
                    .build()
                    .toString();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie).body(existingUser);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue("jwt") String refreshToken) {
        try {
            if(refreshToken.isEmpty()) {
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
            Claims tokenData = jwtService.validateToken(refreshToken);
            Optional<Token> tokenIsDB = tokenRepository.findTokenByRefreshToken(refreshToken);
            String username = tokenData.getSubject();
            return ResponseEntity.ok().body(username);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(@RequestParam String token) {
        try {
            return null;
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}