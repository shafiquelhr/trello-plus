package com.trelloplus.dto;

import com.trelloplus.enums.UserRole;
import lombok.Data;
import java.time.LocalDateTime;

//this will rep. payload from postman as a request:

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String username;
    private String password; // plaintext for now
    private UserRole role;
    private boolean isActive;
    private String phone;
    private String avatarUrl;
    private String bio;
    private String location;
}
