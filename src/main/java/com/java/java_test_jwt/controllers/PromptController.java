package com.java.java_test_jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prompt")
@CrossOrigin(origins = "*")
public class PromptController {
    @PostMapping("/create_prompt")
    public ResponseEntity<?> createPrompt(@RequestBody() Integer userId, String question) {
        try {
            return null;
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping("/get_all_prompts_by_userid")
    public ResponseEntity<?> getAllPromptsByUserId(@RequestParam() Integer userId) {
        try {
            return null;
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}