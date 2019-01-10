package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by computer on 2019/1/10.
 */
@Entity
@Table(name = "remark", schema = "public", catalog = "logistics")
public class RemarkEntity {
    private String remarkId;
    private String reamrkContent;
    private Integer userId;

    @Id
    @Column(name = "remark_id")
    public String getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(String remarkId) {
        this.remarkId = remarkId;
    }

    @Basic
    @Column(name = "reamrk_content")
    public String getReamrkContent() {
        return reamrkContent;
    }

    public void setReamrkContent(String reamrkContent) {
        this.reamrkContent = reamrkContent;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemarkEntity that = (RemarkEntity) o;

        if (remarkId != null ? !remarkId.equals(that.remarkId) : that.remarkId != null) return false;
        if (reamrkContent != null ? !reamrkContent.equals(that.reamrkContent) : that.reamrkContent != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = remarkId != null ? remarkId.hashCode() : 0;
        result = 31 * result + (reamrkContent != null ? reamrkContent.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
