package com.sprintgether.script.management.server.scriptmanagement.model.school;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@ToString(exclude = "listofDepartment")
@NoArgsConstructor
public class School {
    @Id
    String id;
    @Indexed(unique = true)
    String name;
    String acronym;
    String description;
    String logoSchool;
    @DBRef
    Institution ownerInstitution;
    @DBRef
    Institution parentInstitution;
    @DBRef
    List<Department> listofDepartment = new ArrayList<Department>();

    public School(String name, String acronym, String description,
                  Institution ownerInstitution) {
        this.name = name;
        this.acronym = acronym;
        this.description = description;
        this.ownerInstitution = ownerInstitution;
    }
}
