package com.trelloplus.controller;

import com.trelloplus.dto.AuthRequest;
import com.trelloplus.dto.AuthResponse;
import com.trelloplus.dto.RegisterRequest;
import com.trelloplus.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//api controller for logging user in and returning back token:

/**
 * What This Does:
 * -Receives login requests at /api/auth/login
 * -Accepts JSON with username and password
 * -Delegates authentication to AuthService
 * -Returns a JWT wrapped in an AuthResponse if credentials are valid
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
