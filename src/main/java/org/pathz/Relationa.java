package org.pathz;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import org.bson.Document;
import org.bson.types.ObjectId;
public class Relationa {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://admin:admin123@localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("test"); // Вибір бази даних

        // Отримання колекцій для об'єктів Order і Person
        MongoCollection<Document> orderCollection = database.getCollection("orders");
        MongoCollection<Document> personCollection = database.getCollection("actors");

        // Створення об'єкту Person
        Actor person = new Actor();
        person.setName("John Doe");
        person.setAge(15);

        // Створення об'єкту Order і встановлення зв'язку з Person
        Order order = new Order();
        order.setOrderNumber("123456");
        order.setPerson(person);

        // Збереження об'єктів у відповідних колекціях
        Document personDocument = new Document("name", person.getName());
        personCollection.insertOne(personDocument);

        Document orderDocument = new Document("orderNumber", order.getOrderNumber())
                .append("person", new Document("name", person.getName())); // Вставка посилання на Person
        orderCollection.insertOne(orderDocument);

        mongoClient.close();
    }
}


@Data
class Actor {
    private ObjectId id;
    private String name;
    private Integer age;
}

@Data
class Order {
    private ObjectId id;
    private String orderNumber;
    private Actor person;
}
