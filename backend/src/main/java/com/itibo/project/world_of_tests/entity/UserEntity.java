package com.itibo.project.world_of_tests.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.itibo.project.world_of_tests.model.Role;
import com.itibo.project.world_of_tests.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;


public final class UserEntity {

    private static final String ROLE_USER = "ROLE_USER";

    private final String username;
    @Size(min = 8, max = 100)
    private final String password;
    private final String email;
    private final String name;
    private final Date birthday;
    private final String avatar;

    public UserEntity(@JsonProperty("username") String username,
                      @JsonProperty("password") String password,
                      @JsonProperty("email") String email,
                      @JsonProperty("name") String name,
                      @JsonProperty("birthday") Date birthday,
                      @JsonProperty("avatar") String avatar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.avatar = avatar;
    }

    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getEncodedPassword() {
        return Optional.ofNullable(password).map(p -> new BCryptPasswordEncoder().encode(p));
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Date> getBirthday() {
        return Optional.ofNullable(birthday);
    }

    public Optional<String> getAvatar() {
        return Optional.ofNullable(avatar);
    }

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setRole(new Role());
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setBirthday(birthday);
        user.setAvatar(avatar);
        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password, getAuthorities());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> ROLE_USER);
    }

}