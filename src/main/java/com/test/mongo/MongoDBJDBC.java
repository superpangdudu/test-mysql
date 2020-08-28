package com.test.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBC {

    //===================================================================================
    private MongoClient newMongoClient(String host, int port) {
        try {
            return new MongoClient("localhost", 27017);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null)
            return null;

        try {
            return mongoClient.getDatabase(dbName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //===================================================================================
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBJDBC(String host, int port) {
        mongoClient = newMongoClient(host, port);
    }

    //===================================================================================
    public static void main(String args[]) {
        MongoDBJDBC mongoDBJDBC = new MongoDBJDBC("localhost", 27017);
        MongoDatabase mongoDatabase = mongoDBJDBC.getDatabase("mycol");

        MongoCollection<Document> collection =  mongoDatabase.getCollection("test");

        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
    }
}
