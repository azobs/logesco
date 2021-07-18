package com.sprintgether.script.management.server.scriptmanagement.dao.script;

import com.sprintgether.script.management.server.scriptmanagement.model.script.Problem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRepository extends MongoRepository<Problem, String> {
}
