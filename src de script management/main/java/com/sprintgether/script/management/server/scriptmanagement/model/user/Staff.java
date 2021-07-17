package com.sprintgether.script.management.server.scriptmanagement.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Staff {
    @Id
    String id;
    String firstName;
    String lastName;
    String phoneNumber;
    String address;
    @Indexed(unique = true)
    String email;
    String description;
    EnumStaffType staffType;

    @DBRef
    User userAssociated;

    public Staff(String firstName, String lastName, String phoneNumber,
                 String address, String email, String description,
                 EnumStaffType staffType, User userAssociated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.description = description;
        this.staffType = staffType;
        this.userAssociated = userAssociated;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", staffType=" + staffType +
                ", userAssociated=" + userAssociated.getUsername() +
                '}';
    }
}
