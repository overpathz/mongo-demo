package org.pathz.jsonconverterexample;

import org.pathz.Person;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Person person = new Person("Alex", 21, "Java");
        String s = MyJsonConverter.toJson(person);
        System.out.println(s);
    }
}
