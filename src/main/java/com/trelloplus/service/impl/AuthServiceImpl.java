package com.trelloplus.service.impl;

import com.trelloplus.dto.AuthRequest;
import com.trelloplus.dto.RegisterRequest;
import com.trelloplus.model.User;
import com.trelloplus.repository.UserRepository;
import com.trelloplus.service.AuthService;
import com.trelloplus.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder; // Add this


    @Autowired
    public AuthServiceImpl(AuthenticationManager authManager, JwtUtil jwtUtil, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String authenticate(AuthRequest request) {
        try {
            // Now letting Spring authenticate the user
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken
                    (request.getUsername(), request.getPassword()));

            // If successful, Spring returns the authenticated User
            User user = (User) authentication.getPrincipal();

            // Return JWT
            return jwtUtil.generateToken(user);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials: " + e.getMessage());
        }
    }

    @Override
    public void register(RegisterRequest request) {

        // Check for duplicate username
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPasswordHash(hashedPassword);
        user.setRole(request.getRole());
//        user.setActive(request.isActive());
        user.setActive(true); //keep user enabled by defaulylt so they can login without email verifcation or sth like that.
        user.setPhone(request.getPhone());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setBio(request.getBio());
        user.setLocation(request.getLocation());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        userRepo.save(user);
    }

}
