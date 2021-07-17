package com.sprintgether.script.management.server.scriptmanagement.model.script;

import com.sprintgether.script.management.server.scriptmanagement.model.school.Course;
import com.sprintgether.script.management.server.scriptmanagement.model.user.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Paper {
    @Id
    String id;
    String title;
    EnumPaperType paperType;
    @DBRef
    Course concernedCourse;
    @DBRef
    Staff ownerPaper;
    @DBRef
    List<Question> listofQuestion = new ArrayList<Question>();
    @DBRef
    List<Problem> listofProblem = new ArrayList<Problem>();
    @DBRef
    List<Indication> listofIndication = new ArrayList<Indication>();

    public Paper(String title, EnumPaperType paperType, Course concernedCourse, Staff ownerPaper) {
        this.title = title;
        this.paperType = paperType;
        this.concernedCourse = concernedCourse;
        this.ownerPaper = ownerPaper;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", paperType=" + paperType +
                ", listofQuestion=" + listofQuestion +
                ", listofProblem=" + listofProblem +
                ", listofIndication=" + listofIndication +
                '}';
    }
}
