package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.model.Role;

import java.util.List;


public interface RoleService {
    /**
     * Find All Roles presented on server
     *
     * @return list of Role objects
     */
    List<Role> findAll();

    /**
     * Find Role by unique id
     *
     * @param id unique number of Role
     * @return Role object
     */
    Role findOneRoleById(Long id);

    /**
     * Find Role by unique name
     *
     * @param rolename unique name of Role
     * @return Role object
     */
    Role findOneRoleByName(String rolename);

    /**
     * Save new Role into table
     *
     * @param role which would be saved
     */
    void save(Role role);

    /**
     * Delete Role by unique id
     *
     * @param id unique number of Role
     */
    void deleteRoleById(Long id);
}
