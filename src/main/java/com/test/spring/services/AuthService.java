package com.test.spring.services;

import com.test.spring.config.JwtUtil;
import com.test.spring.requests.LoginRequest;
import com.test.spring.requests.RegisterRequest;
import com.test.spring.responses.AuthResponse;
import com.test.spring.data.Role;
import com.test.spring.data.User;
import com.test.spring.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String jwtToken = jwtUtil.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtUtil.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse setAdmin(LoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setRole(Role.ADMIN);
        userRepository.save(user);

        String jwtToken = jwtUtil.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}
