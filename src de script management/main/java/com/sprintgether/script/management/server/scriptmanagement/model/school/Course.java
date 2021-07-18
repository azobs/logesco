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
@ToString(exclude = "ownerCourseOutline")
@NoArgsConstructor
public class Course {
    @Id
    String id;
    String title;
    String courseCode;
    Level ownerLevel;
    int nbreCredit;
    @DBRef
    List<Staff> listofLecturer = new ArrayList<Staff>();
    @DBRef
    CourseOutline ownerCourseOutline;

    public Course(String id, String title, String courseCode, Level ownerLevel, int nbreCredit, List<Staff> listofLecturer, CourseOutline ownerCourseOutline) {
        this.id = id;
        this.title = title;
        this.courseCode = courseCode;
        this.ownerLevel = ownerLevel;
        this.nbreCredit = nbreCredit;
        this.listofLecturer = listofLecturer;
        this.ownerCourseOutline = ownerCourseOutline;
    }

    public Course(String title, String courseCode, Level ownerLevel, List<Staff> listofLecturer) {
        this.title = title;
        this.courseCode = courseCode;
        this.ownerLevel = ownerLevel;
        this.listofLecturer = listofLecturer;
    }

    public Course(String title, String courseCode, Level ownerLevel, int nbreCredit, List<Staff> listofLecturer) {
        this.title = title;
        this.courseCode = courseCode;
        this.ownerLevel = ownerLevel;
        this.nbreCredit = nbreCredit;
        this.listofLecturer = listofLecturer;
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
