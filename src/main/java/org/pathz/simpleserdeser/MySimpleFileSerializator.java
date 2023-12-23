package org.pathz.simpleserdeser;

import org.pathz.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MySimpleFileSerializator {
    public static void serialize(Person person) throws IOException {
        Integer age = person.getAge();
        String name = person.getName();
        String hobby = person.getHobby();
        String all = age + " " + name + " " + hobby;
        Path path = Files.writeString(Path.of("person.txt"), all);
    }

    public static void main(String[] args) throws IOException {
        Person person = new Person("Oleksanr", 21, "java");
        MySimpleFileSerializator.serialize(person);
    }
}
