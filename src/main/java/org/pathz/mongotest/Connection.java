package org.pathz.mongotest;

import com.mongodb.client.*;
import com.mongodb.client.internal.MongoClientImpl;
import org.bson.Document;

public class Connection {
    // 1
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin123@localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("");

        MongoCollection<Document> collection = database.getCollection("humans");
        Document doc = new Document("name", "John Doe");
        collection.insertOne(doc);

        // Create a Document
        Document doc2 = new Document("name", "John Doe")
                .append("address", new Document().append("street", "Kalyny 56"));

        // Insert Document
        collection.insertOne(doc2);

        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.cursor();
        while (cursor.hasNext()) {
            Document next = cursor.next();
            System.out.println(next);
        }
    }
}
