package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by computer on 2019/1/10.
 */
@Entity
@Table(name = "complaint", schema = "public", catalog = "logistics")
public class ComplaintEntity {
    private int complaintId;
    private String complaintContent;
    private int userId;

    @Id
    @Column(name = "complaint_id")
    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    @Basic
    @Column(name = "complaint_content")
    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintEntity that = (ComplaintEntity) o;

        if (complaintId != that.complaintId) return false;
        if (userId != that.userId) return false;
        if (complaintContent != null ? !complaintContent.equals(that.complaintContent) : that.complaintContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = complaintId;
        result = 31 * result + (complaintContent != null ? complaintContent.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
