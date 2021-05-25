package com.peli.coding.test.entities;

import com.peli.coding.test.input.Person;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PersonModifier {

    Map<String, String> lastNameFirstNameMap;
    Map<String, String> modifiedLastNameFirstNameMap;
    List<Person> pickedPersonList;

    public PersonModifier(List<Person> personList, Long l) {
        this.lastNameFirstNameMap = new HashMap<>();
        this.modifiedLastNameFirstNameMap = new HashMap<>();
        this.pickedPersonList = new LinkedList<>();

        int i = 0;

        while ((lastNameFirstNameMap.entrySet().size() < l)) {
            String lastName = personList.get(i).getLastName();
            String firstName = personList.get(i).getFirstName();
            if (!lastNameFirstNameMap.containsKey(lastName) && !lastNameFirstNameMap.containsValue(firstName)) {
                lastNameFirstNameMap.put(lastName, firstName);
                pickedPersonList.add(personList.get(i));
            }
            i++;
        }

        for (int j = 0; j < pickedPersonList.size(); j++) {
            Person p;
            if (j == 0) {
                p = Person.builder()
                        .lastName(pickedPersonList.get(j).getLastName())
                        .firstName(pickedPersonList.get(pickedPersonList.size() - 1).getFirstName())
                        .build();
            } else {
                p = Person.builder()
                        .lastName(pickedPersonList.get(j).getLastName())
                        .firstName(pickedPersonList.get(j - 1).getFirstName())
                        .build();
            }
            modifiedLastNameFirstNameMap.put(p.getLastName(), p.getFirstName());
        }
    }

    public void printModifiedNames(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(String.format("4.1 List of Original Names:%n"));
        for (Map.Entry<String, String> entry : lastNameFirstNameMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            bufferedWriter.write(String.format("\t%s: %s%n", key, value));
        }
        bufferedWriter.write(String.format("4.2 List of Modified Names:%n"));
        for (Map.Entry<String, String> entry : modifiedLastNameFirstNameMap.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            bufferedWriter.write(String.format("\t%s: %s%n", k, v));
        }
    }
}
