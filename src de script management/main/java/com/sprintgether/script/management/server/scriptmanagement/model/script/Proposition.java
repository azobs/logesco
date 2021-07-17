package com.sprintgether.script.management.server.scriptmanagement.model.script;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Proposition {
    @Id
    String id;
    Boolean valid;
    @DBRef
    Content content;

    public Proposition(Boolean valid, Content content) {
        this.valid = valid;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Proposition{" +
                "id='" + id + '\'' +
                ", valid=" + valid +
                ", content=" + content.getValue() +
                '}';
    }
}
