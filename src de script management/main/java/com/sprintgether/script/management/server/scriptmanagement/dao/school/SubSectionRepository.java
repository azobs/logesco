package com.sprintgether.script.management.server.scriptmanagement.dao.school;

import com.sprintgether.script.management.server.scriptmanagement.model.school.SubSection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubSectionRepository extends MongoRepository<SubSection, String> {
}
