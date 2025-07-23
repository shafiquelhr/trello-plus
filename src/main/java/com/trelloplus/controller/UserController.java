package com.trelloplus.controller;

import com.trelloplus.dto.UserDto;
import com.trelloplus.mapper.UserMapper;
import com.trelloplus.model.User;
import com.trelloplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //CREAT a new user;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserDTO(savedUser));
    }

    //GET ALL users;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> dtos = users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    //GET ALL users with no email;
    @GetMapping("/no-email")
    public ResponseEntity<List<UserDto>> getUsersWithNoEmail() {
        List<User> users = userService.getUsersWithNoEmail();
        List<UserDto> dtos = users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
