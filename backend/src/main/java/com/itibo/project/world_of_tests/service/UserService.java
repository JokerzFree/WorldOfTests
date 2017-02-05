package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(Long id);

    User createUser(User user);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);
}
