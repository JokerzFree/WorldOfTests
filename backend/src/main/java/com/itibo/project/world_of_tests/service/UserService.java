package com.itibo.project.world_of_tests.service;


import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {
    /**
     * Find all Users on server
     *
     * @return list of users
     */
    List<User> findAll();

    /**
     * Update user with new values
     *
     * @param user   which would be updated
     * @param params new values of user
     * @return changed User object
     */
    User update(User user, UserEntity params);

    /**
     * Update User Avatar
     *
     * @param user   which avatar would be updated
     * @param avatar path to new image for avatar
     * @return Changed User object
     */
    User updateAvatar(User user, String avatar);

    /**
     * Update when User was logged last time
     *
     * @param user           which updated
     * @param timeZoneOffset of logged user
     * @return Changed User object
     */
    User updateLastLoginTime(User user, String timeZoneOffset);

    /**
     * Find user by unique id
     *
     * @param id unique number for User
     * @return User object
     */
    User findUser(Long id);

    /**
     * Find user by unique name
     *
     * @param username unique name for User
     * @return Optional<User> value (may be null)
     */
    Optional<User> findUser(String username);

    /**
     * Create user with some presented params
     *
     * @param userEntity user params for new user
     * @return created user object
     */
    User createUser(UserEntity userEntity);

    /**
     * Delete specific user
     *
     * @param user which would be deleted
     */
    void deleteUser(User user);

    /**
     * Delete user by unique id
     *
     * @param id unique number of User
     */
    void deleteUser(Long id);
}