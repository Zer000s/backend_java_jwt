package com.java.java_test_jwt.dtos;

public class TokenDTO {
    private Long userId;
    private String refreshToken;

    public TokenDTO(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
