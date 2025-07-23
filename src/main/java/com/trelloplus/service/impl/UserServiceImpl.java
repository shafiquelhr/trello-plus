package com.trelloplus.service.impl;


import com.trelloplus.model.User;
import com.trelloplus.repository.UserRepository;
import com.trelloplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersWithNoEmail() {
        return userRepository.findByEmailIsNull();
    }
}
