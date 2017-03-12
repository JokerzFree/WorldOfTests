package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.model.Role;
import com.itibo.project.world_of_tests.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Andrew on 12.03.2017.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findOneRoleById(Long id) {
        return roleRepository.findOneRoleById(id);
    }

    @Override
    public Role findOneRoleByName(String roleName){
        return roleRepository.findOneRoleByName(roleName);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.delete(id);
    }
}
