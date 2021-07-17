package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.Option;
import com.sprintgether.script.management.server.scriptmanagement.model.school.School;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OptionRepository extends MongoRepository<Option, String> {
    Optional<Option> findOptionByName(String name);
    Optional<Option> findOptionByAcronym(String acronym);
}
