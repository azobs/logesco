package com.sprintgether.script.management.server.scriptmanagement.model.script;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Indication {
    @Id
    String id;
    @DBRef
    Content content;

    public Indication(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Indication{" +
                "id='" + id + '\'' +
                ", content=" + content.getValue() +
                '}';
    }
}
