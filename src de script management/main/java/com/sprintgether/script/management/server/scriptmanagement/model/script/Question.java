package com.sprintgether.script.management.server.scriptmanagement.model.script;

import com.sprintgether.script.management.server.scriptmanagement.model.school.*;
import com.sprintgether.script.management.server.scriptmanagement.model.user.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Question {
    @Id
    String id;
    EnumQuestionType questionType;
    EnumLevelofDifficulty levelofDifficulty;
    /**********************
     * A question must be proposed by a staff. Any staff except class perfect can propose a question
     * for any course.
     */
    @DBRef
    Staff ownerQuestion;
    /******
     * A question can concerned a paragraph, a subsection, a section, a module, a chapter or
     * a whole course.
     * If it concerns a whole course then concernedModule, concernedChapter, concernedSection, concernedSubSection,
     * and concernedParagraph must be null. Only concernedCourse must have a value.
     * BUT
     * If it concerns a Module then concernedChapter, concernedSection, concernedSubSection,
     * and concernedParagraph must be null. Only concernedCourse and concernedModule must have a value.
     * If it concerns a chapter then concernedSection, concernedSubSection,
     * and concernedParagraph must be null. Only concernedCourse, concernedModule and concernedChapter
     * must have a value.
     * If it concerns a Section then concernedSection, concernedSubSection,
     * and concernedParagraph must be null. Only concernedCourse, concernedModule, concernedChapter,
     *  and concernedSection must have a value.
     * If it concerns a SubSection then concernedSubSection,
     * and concernedParagraph must be null. Only concernedCourse, concernedModule, concernedChapter,
     * concernedSection, concernedSubSection must have a value.
     * If it concerns a Paragraph then
     * concernedCourse, concernedModule, concernedChapter,
     * concernedSection, concernedSubSection and concernedParagraph must have a value.
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
     * A question can have a list of proposition if it's a MCQ otherwise the field
     * must be null
     */
    @DBRef
    List<Proposition> listofProposition = new ArrayList<Proposition>();
    /****
     * A question can have many indications than allows to get the answer
     */
    @DBRef
    List<Indication> listofIndication = new ArrayList<Indication>();
    /*****
     * A question must have his proper content
     */
    @DBRef
    Content content;

    /*****************
     * A question must have a list of answer. At least one.
     */
    @DBRef
    List<Answer> listofAnswer = new ArrayList<>();


    public Question(EnumQuestionType questionType, EnumLevelofDifficulty levelofDifficulty,
                    Staff ownerQuestion, Course concernedCourse, Module concernedModule,
                    Chapter concernedChapter, Section concernedSection, SubSection concernedSubSection,
                    Paragraph concernedParagraph, List<Proposition> listofProposition,
                    List<Indication> listofIndication, Content content, List<Answer> listofAnswer) {
        this.questionType = questionType;
        this.levelofDifficulty = levelofDifficulty;
        this.ownerQuestion = ownerQuestion;
        this.concernedCourse = concernedCourse;
        this.concernedModule = concernedModule;
        this.concernedChapter = concernedChapter;
        this.concernedSection = concernedSection;
        this.concernedSubSection = concernedSubSection;
        this.concernedParagraph = concernedParagraph;
        this.listofProposition = listofProposition;
        this.listofIndication = listofIndication;
        this.content = content;
        this.listofAnswer = listofAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", questionType=" + questionType +
                ", levelofDifficulty=" + levelofDifficulty +
                ", concernedCourse=" + concernedCourse.getTitle() +
                ", concernedChapter=" + concernedChapter.getTitle() +
                ", concernedSection=" + concernedSection.getTitle() +
                ", concernedSubSection=" + concernedSubSection.getTitle() +
                ", concernedParagraph=" + concernedParagraph.getTitle() +
                ", listofProposition=" + listofProposition +
                ", listofIndication=" + listofIndication +
                ", content=" + content.getValue() +
                '}';
    }
}
