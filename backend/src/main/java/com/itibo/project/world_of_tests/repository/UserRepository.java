package com.itibo.project.world_of_tests.repository;

import com.itibo.project.world_of_tests.model.Role;
import com.itibo.project.world_of_tests.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
