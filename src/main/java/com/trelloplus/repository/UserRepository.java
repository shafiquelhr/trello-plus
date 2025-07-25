package com.trelloplus.repository;

import com.trelloplus.dto.UserDto;
import com.trelloplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //UserDto findByEmail(String email);
}
