package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.Paragraph;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParagraphRepository extends MongoRepository<Paragraph, String> {
}
