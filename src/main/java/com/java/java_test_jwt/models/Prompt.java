package com.java.java_test_jwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "prompt")
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String question;
    private String answer;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}