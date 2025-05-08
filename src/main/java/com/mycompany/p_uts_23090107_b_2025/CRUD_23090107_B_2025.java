/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p_uts_23090107_b_2025;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author windiwitari
 */
public class CRUD_23090107_B_2025 {
    public static MongoClient getClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    public static MongoCollection<Document> getCustomerCollection() {
        MongoDatabase database = getClient().getDatabase("uts_23090107_B_2025");
        return database.getCollection("coll_23090107_B_2025");
    }
    public static void main(String[] args) {
    CRUD_23090107_B_2025.main(args);
}
    
}
