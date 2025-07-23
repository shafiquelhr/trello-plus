package com.trelloplus.service;

import com.trelloplus.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();

    User createUser(User user);

    List<User> getUsersWithNoEmail();
}

