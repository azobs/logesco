package com.sprintgether.script.management.server.scriptmanagement.model.script;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Content {
    @Id
    String id;
    String value;
    EnumContentType contentType;

    public Content(String value, EnumContentType contentType) {
        this.value = value;
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", contentType=" + contentType +
                '}';
    }
}
