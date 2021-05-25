package com.peli.coding.test;

import com.peli.coding.test.entities.PersonDataset;
import com.peli.coding.test.entities.PersonModifier;
import com.peli.coding.test.input.FileManager;
import com.peli.coding.test.input.Person;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        final String RESOURCE_NAME_INPUT = "coding-test-data.txt";
        final String RESOURCE_NAME_OUTPUT = "results.txt";

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            File inputFile = FileManager.getFile(RESOURCE_NAME_INPUT);
            File outputFile = new File(RESOURCE_NAME_OUTPUT);

            bufferedReader = new BufferedReader(new FileReader(inputFile));
            bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

            String line;
            List<Person> list = new LinkedList();
            while (((line = bufferedReader.readLine()) != null)) {
                Person person = getPerson(line);
                list.add(person);
                bufferedReader.readLine();
            }

            PersonDataset personDataset = new PersonDataset(list);
            personDataset.printCardinality(bufferedWriter);
            personDataset.printTopTen(bufferedWriter);

            PersonModifier personModifier = new PersonModifier(list, 25L);
            personModifier.printModifiedNames(bufferedWriter);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private static Person getPerson(String string) {
        return new Person(string);
    }

}
