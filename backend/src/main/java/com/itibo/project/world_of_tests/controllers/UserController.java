package com.itibo.project.world_of_tests.controllers;

import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.model.Role;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.repository.RoleRepository;
import com.itibo.project.world_of_tests.repository.UserRepository;
import com.itibo.project.world_of_tests.security.JwtTokenHandler;
import com.itibo.project.world_of_tests.security.UserAuthentication;
import com.itibo.project.world_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtTokenHandler jwtTokenHandler;
    private final CurrentUser currentUser;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, JwtTokenHandler jwtTokenHandler, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.userService = userService;
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
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    //@RequestHeader(value="x-auth-token") String xAuthToken
    public ResponseEntity<User> getCurrentUser() {
        return new ResponseEntity<>(currentUser.getCurrentUser(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody UserEntity params) {
        userService.createUser(params);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestParam Map<String, String> params) {
        userService.deleteUser(Long.parseLong(params.get("id")));
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/addUserRole", method = RequestMethod.POST)
    public ResponseEntity addUserRole(@RequestParam Map<String, String> params) {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/deleteUserRole", method = RequestMethod.POST)
    public ResponseEntity deleteUserRole(@RequestParam Map<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(headers, HttpStatus.OK);
    }
}
