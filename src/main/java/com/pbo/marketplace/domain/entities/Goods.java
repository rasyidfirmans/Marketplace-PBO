package com.pbo.marketplace.domain.entities;

import com.google.gson.annotations.SerializedName;
import com.pbo.marketplace.domain.interfaces.GoodsInterface;

public class Goods implements GoodsInterface {
    @SerializedName("goods_picture")
    private String goodsPicturePath;
    @SerializedName("goods_name")
    private String goodsName;
    @SerializedName("goods_price")
    private long goodsPrice;
    @SerializedName("seller_region")
    private String sellerRegion;
    @SerializedName("goods_rating")
    private float goodsrating;

    public Goods(String goodsPicturePath, String goodsName, long goodsPrice, String sellerRegion, float goodsrating) {
        this.goodsPicturePath = goodsPicturePath;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.sellerRegion = sellerRegion;
        this.goodsrating = goodsrating;
    }

    @Override
    public String getGoodsPicturePath() {
        return goodsPicturePath;
    }

    @Override
    public String getGoodsName() {
        return goodsName;
    }

    @Override
    public long getGoodsPrice() {
        return goodsPrice;
    }

    @Override
    public String getSellerRegion() {
        return sellerRegion;
    }

    @Override
    public float getGoodsrating() {
        return goodsrating;
    }
}
