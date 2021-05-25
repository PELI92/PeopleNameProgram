package com.peli.coding.test.input;

import lombok.*;
import org.apache.commons.lang.StringUtils;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    final String DELIMITER = " -- ";
    final String NAME_DELIMITER = ", ";

    private String lastName;
    private String firstName;

    public Person(String s) {
        String fullName = StringUtils.substringBefore(s, DELIMITER);
        String[] splittedNames = fullName.split(NAME_DELIMITER);
        lastName = splittedNames[0];
        firstName = splittedNames[1];
    }

    public String getFullName(){
        return String.format("%s, %s",lastName,firstName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
