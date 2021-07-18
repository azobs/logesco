package com.sprintgether.script.management.server.scriptmanagement.dao.user;

import com.sprintgether.script.management.server.scriptmanagement.model.user.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findTokenByValue(String value);
}
