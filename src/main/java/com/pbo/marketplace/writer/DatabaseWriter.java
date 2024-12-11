package com.pbo.marketplace.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DatabaseWriter {
    public DatabaseWriter(List<?> clazzList, String path) {
        this.JSONWriter(clazzList, path);
    }

    private <T> void JSONWriter(List<T> clazzList, String path) {
        new Thread(() -> {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();

            String json = gson.toJson(clazzList);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                writer.write(json);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }
}
