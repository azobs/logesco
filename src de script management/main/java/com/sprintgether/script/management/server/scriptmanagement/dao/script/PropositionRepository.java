package com.sprintgether.script.management.server.scriptmanagement.dao.script;

import com.sprintgether.script.management.server.scriptmanagement.model.script.Proposition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropositionRepository extends MongoRepository<Proposition, String> {
}
