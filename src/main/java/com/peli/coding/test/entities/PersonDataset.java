package com.peli.coding.test.entities;

import com.peli.coding.test.input.Person;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.*;

public class PersonDataset {
    Map<String, Long> mapCountFullName;
    Map<String, Long> mapCountFirstName;
    Map<String, Long> mapCountLastName;

    public PersonDataset(List<Person> personList) {
        this.mapCountFullName = personList.stream().collect(groupingBy(Person::getFullName, counting()));
        this.mapCountFirstName = personList.stream().collect(groupingBy(Person::getFirstName, counting()));
        this.mapCountLastName = personList.stream().collect(groupingBy(Person::getLastName, counting()));
    }


    public void printCardinality(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(String.format("1. The names cardinality for full, last, and first names:%n"));
        bufferedWriter.write(String.format("\tFull names : %s%n", getDistinctFullNames()));
        bufferedWriter.write(String.format("\tLast names: %s%n", getDistinctLastNames()));
        bufferedWriter.write(String.format("\tFirst names: %s%n", getDistinctFirstNames()));
    }

    private Long getDistinctFullNames() {
        return countDistinct(mapCountFullName);
    }

    private Long getDistinctFirstNames() {
        return countDistinct(mapCountFirstName);
    }

    private Long getDistinctLastNames() {
        return countDistinct(mapCountLastName);
    }

    private Long countDistinct(Map<String, Long> map) {
        return Long.valueOf(map.keySet().size());
    }


    public void printTopTen(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(String.format("2. The most common last names are:%n"));
        for (String s : getTopTenLastNames()) {
            bufferedWriter.write(s);
        }

        bufferedWriter.write(String.format("3. The most common first names are:%n"));
        for (String s : getTopTenFirstNames()) {
            bufferedWriter.write(s);
        }
    }

    private List<String> getTopTenFirstNames() {
        return getTopTen(this.mapCountFirstName);
    }

    private List<String> getTopTenLastNames() {
        return getTopTen(this.mapCountLastName);
    }

    private List<String> getTopTen(Map<String, Long> map) {
        Map<String, Long> filteredMap = map.entrySet().stream()
                .sorted(comparingByValue(reverseOrder()))
                .limit(10)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        return filteredMap.entrySet().stream()
                .sorted(comparingByValue(reverseOrder()))
                .map(e -> buildResponse(e.getKey(), e.getValue()))
                .collect(toList());
    }

    private String buildResponse(String s, Long l) {
        return String.format("\t%s: %s%n", s, l);
    }

}
