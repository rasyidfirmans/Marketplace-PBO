package com.pbo.marketplace.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pbo.marketplace.domain.entities.Cart;
import com.pbo.marketplace.retrieve.DatabaseRetrieve;

public class DatabaseWriter {
    public <T> void JSONWriter(T clazz, String path) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        DatabaseRetrieve databaseRetriever = new DatabaseRetrieve();
        List<Cart> cartList = new ArrayList<>();

        cartList = databaseRetriever.JSONParser(Cart.class, "src/main/java/com/pbo/marketplace/database/carts.json");

        // If the file is empty, create a new list again
        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        cartList.add((Cart) clazz);

        String json = gson.toJson(cartList);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
