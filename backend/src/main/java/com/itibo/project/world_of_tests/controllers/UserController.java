package com.itibo.project.world_of_tests.controllers;

import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.exceptions.UserExceptions.UserDeleteException;
import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.repository.UserRepository;
import com.itibo.project.world_of_tests.security.JwtTokenHandler;
import com.itibo.project.world_of_tests.service.RoleService;
import com.itibo.project.world_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtTokenHandler jwtTokenHandler;
    private final CurrentUser currentUser;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, RoleService roleService, JwtTokenHandler jwtTokenHandler, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtTokenHandler = jwtTokenHandler;
        this.currentUser = currentUser;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers =  userService.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUser(id);
        user.getConvertedLastLogin();
        user.getConvertedBirthday();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrentUser() {
        return new ResponseEntity<>(currentUser.getCurrentUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<User> update(@Valid @RequestBody UserEntity params) {
        User user = currentUser.getCurrentUser();
        userService.update(user, params);
        return new ResponseEntity<>(currentUser.getCurrentUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateEmail", method = RequestMethod.POST)
    public ResponseEntity<User> updateEmail(@RequestBody Map<String, String> params){
        User user = currentUser.getCurrentUser();
        userService.updateEmail(user, params.get("email"));
        return new ResponseEntity<>(currentUser.getCurrentUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResponseEntity<User> updatePassword(@RequestBody Map<String, String> params){
        User user = currentUser.getCurrentUser();
        userService.updatePassword(user, params.get("password"));
        return new ResponseEntity<>(currentUser.getCurrentUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/lastLoginTime", method = RequestMethod.POST)
    public ResponseEntity setLastLoginTime(@RequestBody Map<String, String> params) {
        User user = currentUser.getCurrentUser();
        userService.updateLastLoginTime(user, params.get("offset"));
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody UserEntity params) {
        userService.createUser(params);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteUser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (currentUser.getCurrentUser().getId().equals(id)){
            throw new UserDeleteException("You cannot delete this user");
        }
        userService.deleteUser(id);
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/addUserRole", method = RequestMethod.POST)
    public ResponseEntity addUserRole(@RequestParam Map<String, String> params) {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteUserRole/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserRole(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }
}
