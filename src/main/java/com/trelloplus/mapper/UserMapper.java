package com.trelloplus.mapper;

import com.trelloplus.dto.UserDto;
import com.trelloplus.enums.Role;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    //to dto for controller layer
    public UserDto toUserDTO(User user) {

        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getProjectUnderManagement(),
                user.getUsername(),
                user.getPasswordHash(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getLastLogin(),
                user.isActive(),
                user.getPhone(),
                user.getAvatarUrl(),
                user.getBio(),
                user.getLocation()
        );

    }

    //to User entity for serive and repo layers
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(Role.valueOf(String.valueOf(dto.getRole())));
        user.setProjectUnderManagement(dto.getProjectUnderManagement());
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPasswordHash());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setLastLogin(dto.getLastLogin());
        user.setActive(dto.isActive());
        user.setPhone(dto.getPhone());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setBio(dto.getBio());
        user.setLocation(dto.getLocation());
        return user;
    }

}