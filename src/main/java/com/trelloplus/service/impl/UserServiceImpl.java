package com.trelloplus.service.impl;

import com.trelloplus.exception.UserNotFoundException;
import com.trelloplus.model.User;
import com.trelloplus.repository.UserRepository;
import com.trelloplus.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO: CREATE CUSTOM EXCEPTIONS

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
    @Transactional
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        // Force initialization of the lazy collection
        users.forEach(user -> user.getProjectsManaged().size()); // triggers the loading safely

        return users;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
