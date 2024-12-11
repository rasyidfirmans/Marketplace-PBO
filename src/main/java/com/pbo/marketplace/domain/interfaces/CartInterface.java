package com.pbo.marketplace.domain.interfaces;

import java.util.List;

import com.pbo.marketplace.domain.entities.Goods;

public interface CartInterface {

    public String getEmail();

    public long getSubtotal();

    public long getTax();

    public long getTotal();

    public List<Integer> getQuantity();

    public List<Goods> getGoodsList();
}
