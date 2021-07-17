package com.sprintgether.script.management.server.scriptmanagement.model.script;

import com.sprintgether.script.management.server.scriptmanagement.model.school.*;
import com.sprintgether.script.management.server.scriptmanagement.model.user.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Problem {
    @Id
    String id;
    EnumProblemType problemType;
    EnumLevelofDifficulty levelofDifficulty;
    /**********************
     * A question must be proposed by a staff. Any staff except class perfect can propose a question
     * for any course.
     */
    @DBRef
    Staff ownerProblem;
    /******
     * A question can concerned a paragraph, a subsection, a section, a chapter or
     * a whole course. Refer to question class to complete explanation about those field.
     */
    @DBRef
    Course concernedCourse;
    @DBRef
    Module concernedModule;
    @DBRef
    Chapter concernedChapter;
    @DBRef
    Section concernedSection;
    @DBRef
    SubSection concernedSubSection;
    @DBRef
    Paragraph concernedParagraph;
    /*****
     * A problem must have a list of question.
     */
    @DBRef
    List<Question> listofQuestion = new ArrayList<Question>();
    /****
     * A question can have many indications than allows to get the answer
     */
    @DBRef
    List<Indication> listofIndication = new ArrayList<Indication>();

    public Problem(EnumProblemType problemType, EnumLevelofDifficulty levelofDifficulty, Staff ownerProblem,
                   Course concernedCourse,
                   Module concernedModule, Chapter concernedChapter, Section concernedSection,
                   SubSection concernedSubSection, Paragraph concernedParagraph,
                   List<Question> listofQuestion, List<Indication> listofIndication) {
        this.problemType = problemType;
        this.levelofDifficulty = levelofDifficulty;
        this.ownerProblem = ownerProblem;
        this.concernedCourse = concernedCourse;
        this.concernedModule = concernedModule;
        this.concernedChapter = concernedChapter;
        this.concernedSection = concernedSection;
        this.concernedSubSection = concernedSubSection;
        this.concernedParagraph = concernedParagraph;
        this.listofQuestion = listofQuestion;
        this.listofIndication = listofIndication;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id='" + id + '\'' +
                ", problemType=" + problemType +
                ", concernedCourse=" +concernedCourse.getTitle() +
                ", concernedChapter=" + concernedChapter.getTitle() +
                ", concernedSection=" + concernedSection.getTitle() +
                ", concernedSubSection=" + concernedSubSection.getTitle() +
                ", concernedParagraph=" + concernedParagraph.getTitle() +
                ", listofQuestion=" + listofQuestion +
                ", listofIndication=" + listofIndication +
                '}';
    }
}
