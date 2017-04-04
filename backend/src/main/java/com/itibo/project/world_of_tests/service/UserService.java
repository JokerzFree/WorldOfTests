package com.itibo.project.world_of_tests.service;


import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {
    List<User> findAll();

    User update(User user, UserEntity params);

    User updateAvatar(User user, String avatar);

    User updateLastLoginTime(User user, String timeZoneOffset);

    User findUser(Long id);

    Optional<User> findUser(String username);

    User createUser(UserEntity userEntity);

    void deleteUser(User user);

    void deleteUser(Long id);
}