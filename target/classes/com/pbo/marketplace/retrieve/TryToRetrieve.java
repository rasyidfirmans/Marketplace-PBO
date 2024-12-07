package com.pbo.marketplace.retrieve;

import java.util.List;
import java.util.ArrayList;

import com.pbo.marketplace.domain.entities.Goods;

public class TryToRetrieve {
    public static void main(String[] args) {
        DatabaseRetrieve retrieve = new DatabaseRetrieve();

        List<Goods> listGoods = new ArrayList<>();

        listGoods = retrieve.JSONParser(Goods.class, "src/main/java/com/pbo/marketplace/database/goods.json");

        if (listGoods.isEmpty()) {
            System.out.println("No goods found.");
        } else {
            for (Goods goods : listGoods) {
                System.out.println("Goods Name: " + goods.getGoodsName());
                System.out.println("Goods Price: " + goods.getGoodsPrice());
                System.out.println("Seller Region: " + goods.getSellerRegion());
                System.out.println("Goods Rating: " + goods.getGoodsrating());
                System.out.println();
            }
        }
    }
}
