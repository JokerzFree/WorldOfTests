package com.itibo.project.world_of_tests.repository;

import com.itibo.project.world_of_tests.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
