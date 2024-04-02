package org.question5.main;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.result.InsertOneResult;

import org.bson.Document;

import org.bson.types.ObjectId;
import org.question5.documents.Address;
import org.question5.documents.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;

public class Main {
  private static int calcAge(String birthday){
    return Period.between(LocalDate.parse(birthday),LocalDate.now()).getYears();
  }
  public static void main(String[] args){
    String uri = "mongodb://localhost:27017";
    try (MongoClient mongoClient = MongoClients.create(uri)) {

      byte[] jsonBytes = Files.readAllBytes(Path.of("resources\\testFile.json"));
      String jsonString = new String(jsonBytes);
      Document person = Document.parse(jsonString);
      Document address = (Document) person.get("address");

      MongoDatabase database = mongoClient.getDatabase("question5");
      MongoCollection<Document> persons = database.getCollection("persons");
      IndexOptions indexOptions = new IndexOptions().unique(true);
      persons.createIndex(new Document("email",1), indexOptions);
      persons.createIndex(new Document("cpf",1), indexOptions);

      try {
        InsertOneResult result = persons.insertOne(
                new Person()
                        .append("_id", new ObjectId())
                        .append("name", person.get("name"))
                        .append("age", calcAge(person.getString("birthday")))
                        .append("phone", person.get("phone"))
                        .append("height", person.get("height"))
                        .append("email", person.get("email"))
                        .append("cpf", person.get("cpf"))
                        .append("birthday", person.get("birthday"))
                        .append("address",
                                new Address()
                                        .append("zip", address.get("zip"))
                                        .append("street", address.get("street"))
                                        .append("complement", address.get("complement"))
                                        .append("neighborhood", address.get("neighborhood"))
                                        .append("city", address.get("city"))
                                        .append("state", address.get("state"))
                                        .append("country", address.get("country"))
                        )
        );
        System.out.println("Success! Inserted document id: " + result.getInsertedId());
      }catch (MongoException me){
        System.err.println("Unable to insert due to an error: " + me);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}