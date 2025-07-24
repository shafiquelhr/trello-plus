package com.trelloplus.service;

import com.trelloplus.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);
}
