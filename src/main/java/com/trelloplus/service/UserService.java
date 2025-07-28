package com.trelloplus.service;

import com.trelloplus.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    List<User> findUsersByRole(String roleName);
}
