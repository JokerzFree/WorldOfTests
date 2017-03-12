package com.itibo.project.world_of_tests.service;


import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.model.Role;

import java.util.List;


public interface RoleService {
    List<Role> findAll();
    Role findOneRoleById(Long id);
    Role findOneRoleByName(String rolename);
    void save(Role role);
    void deleteRoleById(Long id);
}
