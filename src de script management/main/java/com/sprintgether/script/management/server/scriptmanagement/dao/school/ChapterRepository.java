package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChapterRepository  extends MongoRepository<Chapter, String> {
}
