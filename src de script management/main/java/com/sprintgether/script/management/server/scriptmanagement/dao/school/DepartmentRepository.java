package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.Department;
import com.sprintgether.script.management.server.scriptmanagement.model.school.School;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    Optional<Department> findDepartmentByName(String name);
    Optional<Department> findDepartmentByAcronym(String acronym);
}
