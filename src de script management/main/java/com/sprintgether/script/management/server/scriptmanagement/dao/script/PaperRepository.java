package com.sprintgether.script.management.server.scriptmanagement.dao.script;

import com.sprintgether.script.management.server.scriptmanagement.model.script.Paper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaperRepository extends MongoRepository<Paper, String> {

}
