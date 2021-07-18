package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository  extends MongoRepository<Course, String> {
    Optional<Course> findCourseByTitle(String title);
}
