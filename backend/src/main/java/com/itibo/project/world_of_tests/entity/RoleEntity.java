package com.itibo.project.world_of_tests.entity;

import java.io.Serializable;

/**
 * Role Entity for creating or updating Role objects
 */
public class RoleEntity implements Serializable{
    private Long id;
    private String rolename;

    public RoleEntity(){

    }

    public RoleEntity(Long id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
