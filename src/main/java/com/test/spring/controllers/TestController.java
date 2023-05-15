package com.test.spring.controllers;

import com.test.spring.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World from secured endpoint! User: " + SecurityContextHolder.getContext().getAuthentication().getName() + "with Role: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
    }
}
