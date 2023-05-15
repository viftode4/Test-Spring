package com.test.spring.controllers;

import com.test.spring.requests.LoginRequest;
import com.test.spring.requests.RegisterRequest;
import com.test.spring.responses.AuthResponse;
import com.test.spring.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/setAdmin")
    public ResponseEntity<AuthResponse> setAdmin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.setAdmin(request));
    }
}
