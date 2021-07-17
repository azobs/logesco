package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.School;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SchoolRepository extends MongoRepository<School, String> {
    Optional<School> findSchoolByName(String name);
    Optional<School> findSchoolByAcronym(String acronym);
}
