package com.trelloplus.service.impl;

import com.trelloplus.enums.UserRole;
import com.trelloplus.exception.UserNotFoundException;
import com.trelloplus.model.User;
import com.trelloplus.repository.UserRepository;
import com.trelloplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        // secure password before saving
        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);

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

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
        userRepository.delete(user);
    }

    @Override
    public List<User> findUsersByRole(String roleName) {
        try {
            UserRole role = UserRole.valueOf(roleName.toUpperCase()); // Validate enum
            List<User> users = userRepository.findByRole(role);

            if (users.isEmpty()) {
                throw new RuntimeException("No users found with role: " + roleName);
            }

            return users;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + roleName);
        }
    }


}
