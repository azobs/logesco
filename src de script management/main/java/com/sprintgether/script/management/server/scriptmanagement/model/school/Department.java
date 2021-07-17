package com.sprintgether.script.management.server.scriptmanagement.model.school;

import com.sprintgether.script.management.server.scriptmanagement.model.user.Staff;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@ToString(exclude = "listofOption")
@NoArgsConstructor
public class Department {
    @Id
    String id;
    String name;
    String acronym;
    String description;
    @DBRef
    School ownerSchool;
    @DBRef
    Staff hod;
    @DBRef
    List<Option> listofOption = new ArrayList<Option>();

    public Department(String name, String acronym, String description,
                      School ownerSchool, Staff hod) {
        this.name = name;
        this.acronym = acronym;
        this.description = description;
        this.ownerSchool = ownerSchool;
        this.hod = hod;
    }
}
