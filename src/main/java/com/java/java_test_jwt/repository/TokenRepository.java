package com.java.java_test_jwt.repository;

import com.java.java_test_jwt.models.Token;
import com.java.java_test_jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByRefreshToken(String refreshToken);

    Optional<Token> removeTokenByRefreshToken(String refreshToken);
}