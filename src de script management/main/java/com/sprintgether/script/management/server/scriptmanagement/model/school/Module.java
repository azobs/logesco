package com.sprintgether.script.management.server.scriptmanagement.model.school;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@ToString(exclude = "listofChapter")
public class Module {
    @Id
    String id;
    int moduleOrder;
    String title;
    @DBRef
    CourseOutline ownerCourseOutline;
    @DBRef
    List<Chapter> listofChapter = new ArrayList<Chapter>();

    public Module(String title, int moduleOrder, CourseOutline ownerCourseOutline) {
        this.title = title;
        this.moduleOrder = moduleOrder;
        this.ownerCourseOutline = ownerCourseOutline;
    }

    public String getTitle() {
        try{
            return this.title;
        }
        catch(NullPointerException e){
            return "";
        }
    }
}
