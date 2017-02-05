package com.itibo.project.world_of_tests.repository;

import com.itibo.project.world_of_tests.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByEmail(String email);

    User findOneByEmailAndPassword(String email, String password);
}
