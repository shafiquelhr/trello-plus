package com.trelloplus.repository;

import com.trelloplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // add custom queries here like find users with null.


}
