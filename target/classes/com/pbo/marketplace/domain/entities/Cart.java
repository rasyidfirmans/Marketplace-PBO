package com.pbo.marketplace.domain.entities;

import java.util.List;

import com.pbo.marketplace.domain.interfaces.CartInterface;

public class Cart implements CartInterface {
    private String name;
    private String email;
    private long subtotal;
    private long tax;
    private long total;
    private List<Integer> quantity;
    private List<Goods> goodsList;

    public Cart(String name, String email, long subtotal, long tax, long total, List<Integer> quantity,
            List<Goods> goodsList) {
        this.name = name;
        this.email = email;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.quantity = quantity;
        this.goodsList = goodsList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public long getSubtotal() {
        return subtotal;
    }

    @Override
    public long getTax() {
        return tax;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public List<Integer> getQuantity() {
        return quantity;
    }

    @Override
    public List<Goods> getGoodsList() {
        return goodsList;
    }
}
