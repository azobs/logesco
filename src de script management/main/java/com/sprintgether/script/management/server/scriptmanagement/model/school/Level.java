package com.sprintgether.script.management.server.scriptmanagement.model.school;

import com.sprintgether.script.management.server.scriptmanagement.model.user.Staff;
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
@ToString(exclude = "listofCourse")
@NoArgsConstructor
public class Level {
    @Id
    String id;
    String name;
    String acronym;
    @DBRef
    Option ownerOption;
    @DBRef
    Staff classPrefect;
    @DBRef
    List<Course> listofCourse = new ArrayList<Course>();

    public Level(String name, String acronym, Option ownerOption,
                 Staff classPrefect) {
        this.name = name;
        this.acronym = acronym;
        this.ownerOption = ownerOption;
        this.classPrefect = classPrefect;
    }
}
