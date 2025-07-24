package com.trelloplus.controller;

import com.trelloplus.dto.UserDto;
import com.trelloplus.mapper.UserMapper;
import com.trelloplus.model.User;
import com.trelloplus.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // POST new user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(userMapper.toUserDto(savedUser), HttpStatus.CREATED);
    }

    // GET all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> dtos = users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}