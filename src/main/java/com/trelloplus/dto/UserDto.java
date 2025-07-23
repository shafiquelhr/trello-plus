package com.trelloplus.dto;

import com.trelloplus.enums.Role;
import com.trelloplus.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private String projectUnderManagement; // used only if role == LEAD
    private String username;
    private String passwordHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private boolean isActive;
    private String phone;
    private String avatarUrl;
    private String bio;
    private String location;

    //old school
    public UserDto(Long id, String name, String email, Role role, String projectUnderManagement, String username, String passwordHash, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLogin, boolean isActive, String phone, String avatarUrl, String bio, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.projectUnderManagement = projectUnderManagement;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.location = location;
    }
}
