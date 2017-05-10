package com.itibo.project.world_of_tests.helpers;

import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Andrew on 12.03.2017.
 */
@Service
public class CurrentUser {
    private final UserService userService;

    @Autowired
    public CurrentUser(UserService userService){
        this.userService = userService;
    }

    /**
     * Return user which try to access to server
     * @return User object
     */
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUser(authentication.getName()).get();
    }
}
