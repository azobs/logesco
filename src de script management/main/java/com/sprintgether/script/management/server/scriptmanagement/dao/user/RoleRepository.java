package com.sprintgether.script.management.server.scriptmanagement.dao.user;

import com.sprintgether.script.management.server.scriptmanagement.model.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findRoleByRoleName(String roleName);
}
