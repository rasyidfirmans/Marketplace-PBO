package com.pbo.marketplace.domain.actionlistener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.pbo.marketplace.domain.entities.Cart;
import com.pbo.marketplace.domain.entities.Goods;
import com.pbo.marketplace.retrieve.DatabaseRetrieve;
import com.pbo.marketplace.writer.DatabaseWriter;

public class AddToCartActionPerformed {
    private String goodsPicturePath;
    private String goodsName;
    private String goodsPrice;
    private String sellerRegion;
    private String goodsRating;
    private String email;
    private long subtotal;
    private long tax;
    private long total;

    public AddToCartActionPerformed(JLabel goodsImage, JLabel goodsName, JLabel goodsPrice, JLabel sellerRegion,
            JLabel goodsRating, java.awt.event.ActionEvent evt) {
        this.evListener(goodsImage, goodsName, goodsPrice, sellerRegion, goodsRating, evt);
    }

    private void evListener(JLabel goodsImage, JLabel goodsName, JLabel goodsPrice, JLabel sellerRegion,
            JLabel goodsRating,
            java.awt.event.ActionEvent evt) {
        new Thread(() -> {
            List<Cart> cartList = new ArrayList<>();
            List<Integer> quantity = new ArrayList<>();
            List<Goods> goodsList = new ArrayList<>();

            DatabaseRetrieve databaseRetrieve = new DatabaseRetrieve();

            cartList = databaseRetrieve.JSONParser(Cart.class, "src/main/java/com/pbo/marketplace/database/carts.json");

            if (cartList == null) {
                cartList = new ArrayList<>();
            }

            try {
                String decodedPath = java.net.URLDecoder.decode(goodsImage.getIcon().toString(), "UTF-8");
                int index = decodedPath.indexOf("/com/pbo/marketplace/assets/");
                if (index != -1) {
                    this.goodsPicturePath = decodedPath.substring(index);
                } else {
                    this.goodsPicturePath = decodedPath;
                }
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            this.goodsName = goodsName.getText();
            this.goodsPrice = goodsPrice.getText();
            this.sellerRegion = sellerRegion.getText();
            this.goodsRating = goodsRating.getText();

            this.email = "rasyidnf.id@gmail.com";

            boolean isCartExist = false;
            boolean isGoodsExist = false;

            for (Cart cart : cartList) {
                if (cart.getEmail().equals(this.email)) {
                    isCartExist = true;

                    goodsList = cart.getGoodsList();
                    quantity = cart.getQuantity();

                    try {
                        String priceWithoutCurrency = this.goodsPrice.replaceAll("[^\\d]", "");
                        String[] ratingParts = this.goodsRating.split("/");
                        float rating = Float.parseFloat(ratingParts[0].trim());

                        for (Goods goods : goodsList) {
                            if (goods.getGoodsName().equals(this.goodsName)) {
                                isGoodsExist = true;
                                int goodsIndex = goodsList.indexOf(goods);
                                quantity.set(goodsIndex, quantity.get(goodsIndex) + 1);
                                break;
                            }
                        }

                        if (!isGoodsExist) {
                            quantity.add(1);
                            goodsList.add(
                                    new Goods(this.goodsPicturePath, this.goodsName,
                                            Long.parseLong(priceWithoutCurrency),
                                            this.sellerRegion, rating));
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        String priceWithoutCurrency = this.goodsPrice.replaceAll("[^\\d]", "");

                        this.subtotal = cart.getSubtotal();
                        this.subtotal += Long.parseLong(priceWithoutCurrency);
                        this.tax = (long) (this.subtotal * 0.1);
                        this.total = this.subtotal + this.tax;
                        cartList.set(cartList.indexOf(cart),
                                new Cart(email, subtotal, tax, total, quantity, goodsList));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                break;
            }

            if (!isCartExist) {
                try {
                    String priceWithoutCurrency = this.goodsPrice.replaceAll("[^\\d]", "");
                    String[] ratingParts = this.goodsRating.split("/");
                    float rating = Float.parseFloat(ratingParts[0].trim());
                    quantity.add(1);
                    goodsList.add(
                            new Goods(this.goodsPicturePath, this.goodsName, Long.parseLong(priceWithoutCurrency),
                                    this.sellerRegion, rating));

                    this.subtotal = 0;
                    this.subtotal += Long.parseLong(priceWithoutCurrency);
                    this.tax = (long) (this.subtotal * 0.1);
                    this.total = this.subtotal + this.tax;
                    cartList.add(new Cart(email, subtotal, tax, total, quantity, goodsList));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            DatabaseWriter databaseWriter = new DatabaseWriter(cartList,
                    "src/main/java/com/pbo/marketplace/database/carts.json");
            javax.swing.JOptionPane.showMessageDialog(null, "Item added to cart");

        }).start();
    }
}
