package com.example.liberaryadmin.database.ObjectClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "IssuedBook")
public class IssuedBook {
    private Integer bookId,customerId;
    private String issuedDate;

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public IssuedBook(Integer bookId, Integer customerId, String issuedDate) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.issuedDate = issuedDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }
}
