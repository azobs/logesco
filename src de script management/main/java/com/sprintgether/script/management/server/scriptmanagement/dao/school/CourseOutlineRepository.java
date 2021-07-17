package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.CourseOutline;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseOutlineRepository  extends MongoRepository<CourseOutline, String> {
}
