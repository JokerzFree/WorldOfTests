package com.itibo.project.world_of_tests.repository;

import com.itibo.project.world_of_tests.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT r FROM Role r WHERE r.id = ?1")
    Role findOneRoleById(Long id);

    @Query(value = "SELECT r FROM Role r WHERE r.rolename = ?1")
    Role findOneRoleByName(String rolename);

    @Modifying
    @Query(value = "DELETE FROM Role r WHERE r.id = ?1")
    void delete(Long id);
}
