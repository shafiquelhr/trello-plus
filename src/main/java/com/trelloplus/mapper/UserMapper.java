package com.trelloplus.mapper;

import com.trelloplus.dto.UserDto;
import com.trelloplus.model.Project;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) return null;

        List<Long> projectIds = user.getProjectsManaged().stream().map(Project::getId).collect(Collectors.toList());

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                projectIds,
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

    public User toEntity(UserDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPasswordHash());
        user.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        user.setUpdatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : LocalDateTime.now());
        user.setLastLogin(dto.getLastLogin() != null ? dto.getLastLogin() : LocalDateTime.now());
        user.setActive(dto.isActive());
        user.setPhone(dto.getPhone());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setBio(dto.getBio());
        user.setLocation(dto.getLocation());

        return user;
    }
}
