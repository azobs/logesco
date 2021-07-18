package com.sprintgether.script.management.server.scriptmanagement.dao.user;

import com.sprintgether.script.management.server.scriptmanagement.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
