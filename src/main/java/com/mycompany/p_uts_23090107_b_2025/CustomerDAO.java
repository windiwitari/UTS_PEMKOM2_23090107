package com.mycompany.p_uts_23090107_b_2025;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author windiwitari
 */
public class CustomerDAO {
    MongoCollection<Document> collection = CRUD_23090107_B_2025.getCustomerCollection();

    // Tambah data customer
    public void tambahCustomer(String nama, String email, String telepon) {
        Document doc = new Document("nama", nama)
                .append("email", email)
                .append("telepon", telepon);
        collection.insertOne(doc);
    }

    // Ambil semua data customer
    public FindIterable<Document> getAllCustomers() {
        return collection.find();
    }

    // Hapus customer berdasarkan email
    public void hapusCustomer(String email) {
        collection.deleteOne(Filters.eq("email", email));
    }

    // Cari customer berdasarkan keyword di nama atau email
    public List<Document> searchCustomers(String keyword) {
        List<Document> results = new ArrayList<>();
        Bson filter = Filters.or(
                Filters.regex("nama", keyword, "i"),
                Filters.regex("email", keyword, "i")
        );

        MongoCursor<Document> cursor = collection.find(filter).iterator();
        try {
            while (cursor.hasNext()) {
                results.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return results;
    }

    // 🔧 Update nama dan telepon customer berdasarkan email
    public void updateCustomer(String email, String newNama, String newTelepon) {
        Bson filter = Filters.eq("email", email);
        Bson updates = Updates.combine(
                Updates.set("nama", newNama),
                Updates.set("telepon", newTelepon)
        );
        collection.updateOne(filter, updates);
    }
}
