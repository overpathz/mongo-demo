package org.pathz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.pathz.jsonconverterexample.MyJsonConverter;
import org.pathz.simpleserdeser.MySimpleFileSerializator;

import java.io.IOException;

public class FetchApiTest {
    public static void main(String[] args) throws IOException {
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin123@localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("persons");

        Person p = new Person("Bohdan", 35, "Swimming");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(p);
        Document parse = Document.parse(jsonString);
//        collection.insertOne(parse);

        FindIterable<Document> documents = collection.find();

        for (Document document : documents) {
            System.out.println(document);
        }

        collection.updateOne(
                Filters.eq("name", "Bohdan"),
                Updates.set("age", 22)
        );

        collection.deleteOne(Filters.eq("name", "Olena"));
    }
}
