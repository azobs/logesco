package com.sprintgether.script.management.server.scriptmanagement.model.school;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@ToString(exclude = "listofModule")
@NoArgsConstructor
public class CourseOutline {
    @Id
    String id;
    String title;
    @DBRef
    Course ownerCourse;
    @DBRef
    List<Module> listofModule = new ArrayList<Module>();

    public CourseOutline(String title) {
        this.title = title;
    }

    public CourseOutline(String title, Course ownerCourse) {
        this.title = title;
        this.ownerCourse = ownerCourse;
    }

    public String getTitle() {
        try{
            return this.title;
        }
        catch(NullPointerException e){
            return "";
        }
    }

    @Override
    public String toString() {
        return "CourseOutline{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
