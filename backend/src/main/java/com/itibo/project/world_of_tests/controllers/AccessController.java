package com.itibo.project.world_of_tests.controllers;

import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.service.UserService;
import com.itibo.project.world_of_tests.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrew on 01.05.2017.
 */
@RestController
@RequestMapping("/api/access")
@CrossOrigin(origins = "http://localhost:3000")
public class AccessController {
    private final StorageService storageService;
    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public AccessController(StorageService storageService, UserService userService, CurrentUser currentUser) {
        this.storageService = storageService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<User> getAccessUsers() {
        User user = currentUser.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/post-add", method = RequestMethod.GET)
    public ResponseEntity<User> getAccessPostAdd() {
        User user = currentUser.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
