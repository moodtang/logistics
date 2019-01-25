package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by computer on 2019/1/10.
 */
@Entity
@Table(name = "info_goods", schema = "public", catalog = "logistics")
public class InfoGoodsEntity {
    private String goodsId;
    private int goodsWeight;
    private String goodsContent;
    private int goodsExpensiveLevel;
    private String orderId;


    @Id
    @Column(name = "goods_id")
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "goods_weight")
    public int getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(int goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    @Basic
    @Column(name = "goods_content")
    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    @Basic
    @Column(name = "goods_expensive_level")
    public int getGoodsExpensiveLevel() {
        return goodsExpensiveLevel;
    }

    public void setGoodsExpensiveLevel(int goodsExpensiveLevel) {
        this.goodsExpensiveLevel = goodsExpensiveLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoGoodsEntity that = (InfoGoodsEntity) o;

        if (goodsWeight != that.goodsWeight) return false;
        if (goodsExpensiveLevel != that.goodsExpensiveLevel) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (goodsContent != null ? !goodsContent.equals(that.goodsContent) : that.goodsContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId != null ? goodsId.hashCode() : 0;
        result = 31 * result + goodsWeight;
        result = 31 * result + (goodsContent != null ? goodsContent.hashCode() : 0);
        result = 31 * result + goodsExpensiveLevel;
        return result;
    }

    @Basic
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
