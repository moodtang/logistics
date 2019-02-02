package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by computer on 2019/1/10.
 */
@Entity
@Table(name = "list_order", schema = "public", catalog = "logistics")
public class ListOrderEntity {
    private String orderId;
    private String requireMethod;
    private Integer requireLevel;
    private String requireTime;
    private String orderDate;
    private String goodsId;
    private String addressFromUser;
    private String addressToUser;
    private String complaintFromUser;
    private String complaintToUser;
    private Integer levelFromUser;
    private Integer levelToUser;
    private String orderFromUser;
    private String orderToUser;
    private String remarkToUser;
    private String remarkFromUser;
    private Integer status;

    @Id
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "require_method")
    public String getRequireMethod() {
        return requireMethod;
    }

    public void setRequireMethod(String requireMethod) {
        this.requireMethod = requireMethod;
    }

    @Basic
    @Column(name = "require_level")
    public Integer getRequireLevel() {
        return requireLevel;
    }

    public void setRequireLevel(Integer requireLevel) {
        this.requireLevel = requireLevel;
    }

    @Basic
    @Column(name = "require_time")
    public String getRequireTime() {
        return requireTime;
    }

    public void setRequireTime(String requireTime) {
        this.requireTime = requireTime;
    }

    @Basic
    @Column(name = "order_date")
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "goods_id")
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "address_from_user")
    public String getAddressFromUser() {
        return addressFromUser;
    }

    public void setAddressFromUser(String addressFromUser) {
        this.addressFromUser = addressFromUser;
    }

    @Basic
    @Column(name = "address_to_user")
    public String getAddressToUser() {
        return addressToUser;
    }

    public void setAddressToUser(String addressToUser) {
        this.addressToUser = addressToUser;
    }

    @Basic
    @Column(name = "complaint_from_user")
    public String getComplaintFromUser() {
        return complaintFromUser;
    }

    public void setComplaintFromUser(String complaintFromUser) {
        this.complaintFromUser = complaintFromUser;
    }

    @Basic
    @Column(name = "complaint_to_user")
    public String getComplaintToUser() {
        return complaintToUser;
    }

    public void setComplaintToUser(String complaintToUser) {
        this.complaintToUser = complaintToUser;
    }

    @Basic
    @Column(name = "level_from_user")
    public Integer getLevelFromUser() {
        return levelFromUser;
    }

    public void setLevelFromUser(Integer levelFromUser) {
        this.levelFromUser = levelFromUser;
    }

    @Basic
    @Column(name = "level_to_user")
    public Integer getLevelToUser() {
        return levelToUser;
    }

    public void setLevelToUser(Integer levelToUser) {
        this.levelToUser = levelToUser;
    }

    @Basic
    @Column(name = "order_from_user")
    public String getOrderFromUser() {
        return orderFromUser;
    }

    public void setOrderFromUser(String orderFromUser) {
        this.orderFromUser = orderFromUser;
    }

    @Basic
    @Column(name = "order_to_user")
    public String getOrderToUser() {
        return orderToUser;
    }

    public void setOrderToUser(String orderToUser) {
        this.orderToUser = orderToUser;
    }

    @Basic
    @Column(name = "remark_to_user")
    public String getRemarkToUser() {
        return remarkToUser;
    }

    public void setRemarkToUser(String remarkToUser) {
        this.remarkToUser = remarkToUser;
    }

    @Basic
    @Column(name = "remark_from_user")
    public String getRemarkFromUser() {
        return remarkFromUser;
    }

    public void setRemarkFromUser(String remarkFromUser) {
        this.remarkFromUser = remarkFromUser;
    }
    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListOrderEntity that = (ListOrderEntity) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (requireMethod != null ? !requireMethod.equals(that.requireMethod) : that.requireMethod != null)
            return false;
        if (requireLevel != null ? !requireLevel.equals(that.requireLevel) : that.requireLevel != null) return false;
        if (requireTime != null ? !requireTime.equals(that.requireTime) : that.requireTime != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (addressFromUser != null ? !addressFromUser.equals(that.addressFromUser) : that.addressFromUser != null)
            return false;
        if (addressToUser != null ? !addressToUser.equals(that.addressToUser) : that.addressToUser != null)
            return false;
        if (complaintFromUser != null ? !complaintFromUser.equals(that.complaintFromUser) : that.complaintFromUser != null)
            return false;
        if (complaintToUser != null ? !complaintToUser.equals(that.complaintToUser) : that.complaintToUser != null)
            return false;
        if (levelFromUser != null ? !levelFromUser.equals(that.levelFromUser) : that.levelFromUser != null)
            return false;
        if (levelToUser != null ? !levelToUser.equals(that.levelToUser) : that.levelToUser != null) return false;
        if (orderFromUser != null ? !orderFromUser.equals(that.orderFromUser) : that.orderFromUser != null)
            return false;
        if (orderToUser != null ? !orderToUser.equals(that.orderToUser) : that.orderToUser != null) return false;
        if (remarkToUser != null ? !remarkToUser.equals(that.remarkToUser) : that.remarkToUser != null) return false;
        if (remarkFromUser != null ? !remarkFromUser.equals(that.remarkFromUser) : that.remarkFromUser != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (requireMethod != null ? requireMethod.hashCode() : 0);
        result = 31 * result + (requireLevel != null ? requireLevel.hashCode() : 0);
        result = 31 * result + (requireTime != null ? requireTime.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (addressFromUser != null ? addressFromUser.hashCode() : 0);
        result = 31 * result + (addressToUser != null ? addressToUser.hashCode() : 0);
        result = 31 * result + (complaintFromUser != null ? complaintFromUser.hashCode() : 0);
        result = 31 * result + (complaintToUser != null ? complaintToUser.hashCode() : 0);
        result = 31 * result + (levelFromUser != null ? levelFromUser.hashCode() : 0);
        result = 31 * result + (levelToUser != null ? levelToUser.hashCode() : 0);
        result = 31 * result + (orderFromUser != null ? orderFromUser.hashCode() : 0);
        result = 31 * result + (orderToUser != null ? orderToUser.hashCode() : 0);
        result = 31 * result + (remarkToUser != null ? remarkToUser.hashCode() : 0);
        result = 31 * result + (remarkFromUser != null ? remarkFromUser.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
