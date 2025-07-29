package com.trelloplus.repository;

import com.trelloplus.dto.UserDto;
import com.trelloplus.enums.UserRole;
import com.trelloplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //this is done to implement logging in functionality:
    Optional<Object> findByUsername(String username);

    List<User> findByRole(UserRole role);
}
