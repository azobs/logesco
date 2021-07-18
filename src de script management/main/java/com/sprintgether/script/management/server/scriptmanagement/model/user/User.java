package com.sprintgether.script.management.server.scriptmanagement.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class User {
    @Id
    String id;
    @Indexed(unique = true)
    String username;
    String password;
    Boolean active;

    /***
     * Un utilisateur a une liste de role qui doit etre charge
     * des sa connexion
     */
    @DBRef
    List<Role> listofRole = new ArrayList<Role>();

    public User(String username, String password, Boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", listofRole=" + listofRole +
                '}';
    }
}
