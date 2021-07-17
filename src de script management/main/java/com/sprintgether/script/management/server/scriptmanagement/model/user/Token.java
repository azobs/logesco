package com.sprintgether.script.management.server.scriptmanagement.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class Token {
    @Id
    String id;
    @Indexed(unique = true)
    String value;
    Date expired;
    EnumTokenType tokenType;
    /*****
     * Un Token doit appartenir à un Utilisateur
     */
    @DBRef
    User ownerUser;

    public Token(String value, Date expired, EnumTokenType tokenType, User ownerUser) {
        this.value = value;
        this.expired = expired;
        this.tokenType = tokenType;
        this.ownerUser = ownerUser;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", expired=" + expired +
                ", tokenType=" + tokenType +
                ", ownerUser=" + ownerUser.getUsername() +
                '}';
    }
}
