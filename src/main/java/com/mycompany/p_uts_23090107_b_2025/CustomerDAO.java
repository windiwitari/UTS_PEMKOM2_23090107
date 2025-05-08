package com.mycompany.p_uts_23090107_b_2025;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
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

    public void tambahCustomer(String nama, String email, String telepon) {
        Document doc = new Document("nama", nama)
                .append("email", email)
                .append("telepon", telepon);
        collection.insertOne(doc);
    }

    public FindIterable<Document> getAllCustomers() {
        return collection.find();
    }

    public void hapusCustomer(String email) {
        collection.deleteOne(new Document("email", email));
    }

    // ✅ Tambahan: Pencarian berdasarkan keyword (nama atau email)
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
}
