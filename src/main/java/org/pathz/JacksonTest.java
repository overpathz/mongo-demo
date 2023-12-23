package org.pathz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
    public static void main(String[] args) {
        Person person = new Person("Oleksanr", 21, "java");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(person);
            System.out.println(jsonString);

            Person person1 = objectMapper.readValue(jsonString, Person.class);
            System.out.println(person1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
