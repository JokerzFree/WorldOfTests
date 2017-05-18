package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.exceptions.EmailException;
import com.itibo.project.world_of_tests.exceptions.PasswordException;
import com.itibo.project.world_of_tests.model.Role;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.repository.RoleRepository;
import com.itibo.project.world_of_tests.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);
        final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        user.ifPresent(detailsChecker::check);
        return user.orElseThrow(() -> new UsernameNotFoundException("user not found."));
    }

    @Override
    public User update(User user, UserEntity params) {
        params.getEmail().ifPresent(user::setUsername);
        params.getEncodedPassword().ifPresent(user::setPassword);
        params.getName().ifPresent(user::setName);
        params.getAvatar().ifPresent(user::setAvatar);
        return userRepository.save(user);
    }

    @Override
    public User updateAvatar(User user, String avatar){
        user.setAvatar(avatar);
        return userRepository.save(user);
    }

    @Override
    public User updateEmail(User user, String email){
        user.setEmail(email);
        try {
            userRepository.save(user);
        } catch (Exception ex){
            throw new EmailException("Email verification failed");
        }
        return user;
    }

    @Override
    public User updatePassword(User user, String password){
        if (BCrypt.checkpw(password, user.getPassword())){
            throw new PasswordException("User password not should be equal to old one");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return userRepository.save(user);
    }

    @Override
    public User updateLastLoginTime(User user, String timeZoneOffset){
        user.setLastLoginTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return userRepository.save(user);
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Optional<User> findUser(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    @Override
    public User createUser(UserEntity userEntity) {
        User user = toUserRole(userEntity);
        Date defaultDate = Date.from(LocalDateTime.of(1970,1,1,1,0,0).atZone(ZoneId.systemDefault()).toInstant());
        user.setLastLoginTime(defaultDate);
        user.setAvatar("none.png");
        return userRepository.save(user);
    }

    private User toUserRole(UserEntity userEntity) {
        User user = userEntity.toUser();
        user.setRoles(new HashSet<Role>());
        Role role = roleRepository.findOneRoleByName("ROLE_USER");
        user.addRole(role);
        return user;
    }
}
