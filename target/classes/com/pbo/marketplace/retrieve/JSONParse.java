package com.pbo.marketplace.retrieve;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JSONParse {
    public <T> List<T> JSONParser(Class<T> clazz, String path) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        List<T> items = new ArrayList<>();

        try {
            FileReader reader = new FileReader(path);
            try {
                Type listType = TypeToken.getParameterized(List.class, clazz).getType();
                items = gson.fromJson(reader, listType);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }
}
