package com.sprintgether.script.management.server.scriptmanagement.dao.user;

import com.sprintgether.script.management.server.scriptmanagement.model.user.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StaffRepository extends MongoRepository<Staff, String> {
    Optional<Staff> findStaffByEmail(String email);
}
