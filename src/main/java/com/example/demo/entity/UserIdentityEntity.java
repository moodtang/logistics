package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by computer on 2019/1/10.
 */
@Entity
@Table(name = "user_identity", schema = "public", catalog = "logistics")
public class UserIdentityEntity {
    private String userId;
    private String userCard;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_card")
    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserIdentityEntity that = (UserIdentityEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userCard != null ? !userCard.equals(that.userCard) : that.userCard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userCard != null ? userCard.hashCode() : 0);
        return result;
    }
}
