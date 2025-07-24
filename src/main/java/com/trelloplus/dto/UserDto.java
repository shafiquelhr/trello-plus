package com.trelloplus.dto;

import com.trelloplus.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private List<Long> projectsManagedIds;
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


    public UserDto(Long id,
                   String name,
                   String email,
                   UserRole role,
                   List<Long> projectsManagedIds,
                   String username,
                   String passwordHash,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt,
                   LocalDateTime lastLogin,
                   boolean isActive,
                   String phone,
                   String avatarUrl,
                   String bio,
                   String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.projectsManagedIds = projectsManagedIds;
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
