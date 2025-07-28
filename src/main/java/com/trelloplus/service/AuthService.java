package com.trelloplus.service;

import com.trelloplus.dto.AuthRequest;
import com.trelloplus.dto.RegisterRequest;

/** This service will handle the core authentication logic, including:
 * -Validating credentials.
 * -Generating a JWT token on successful login.
 */
public interface AuthService {
    String authenticate(AuthRequest request);

    void register(RegisterRequest request);
}