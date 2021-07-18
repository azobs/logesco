package com.sprintgether.script.management.server.scriptmanagement.model.school;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Paragraph {
    @Id
    String id;
    String title;
    int paragraphOrder;
    @DBRef
    SubSection ownerSubSection;

    public Paragraph(String title, int paragraphOrder, SubSection ownerSubSection) {
        this.title = title;
        this.paragraphOrder = paragraphOrder;
        this.ownerSubSection = ownerSubSection;
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
        return "Paragraph{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", ownerSubSection=" + ownerSubSection +
                '}';
    }
}
