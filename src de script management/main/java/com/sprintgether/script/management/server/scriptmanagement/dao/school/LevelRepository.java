package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.Level;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LevelRepository extends MongoRepository<Level, String> {
    Optional<Level> findLevelByName(String name);
    Optional<Level> findLevelByAcronym(String acronym);
}
