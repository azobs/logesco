package com.sprintgether.script.management.server.scriptmanagement.dao.script;

import com.sprintgether.script.management.server.scriptmanagement.model.script.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerRepository extends MongoRepository<Answer, String> {

}
