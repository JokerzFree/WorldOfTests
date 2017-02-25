package com.itibo.project.world_of_tests.service;


import com.itibo.project.world_of_tests.dto.UserDTO;
import com.itibo.project.world_of_tests.model.User;

import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {

    User update(User user, UserDTO params);
    Optional<User> findUser(Long id);
    User createUser(UserDTO userDTO);

}