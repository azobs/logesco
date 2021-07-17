package com.sprintgether.script.management.server.scriptmanagement.dao.script;

import com.sprintgether.script.management.server.scriptmanagement.model.script.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<Content, String> {
}
