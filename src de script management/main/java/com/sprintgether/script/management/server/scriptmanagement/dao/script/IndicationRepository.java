package com.sprintgether.script.management.server.scriptmanagement.dao.script;

import com.sprintgether.script.management.server.scriptmanagement.model.script.Indication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IndicationRepository extends MongoRepository<Indication, String> {
}
