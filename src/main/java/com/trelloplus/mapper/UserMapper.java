package com.trelloplus.mapper;

import com.trelloplus.dto.UserDto;
import com.trelloplus.model.Project;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

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
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setLastLogin(dto.getLastLogin());
        user.setActive(dto.isActive());
        user.setPhone(dto.getPhone());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setBio(dto.getBio());
        user.setLocation(dto.getLocation());

        // We don't manually set projectsManaged here; it is managed by Project entity
        return user;
    }
}
