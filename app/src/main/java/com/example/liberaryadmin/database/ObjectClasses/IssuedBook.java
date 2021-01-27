package com.example.liberaryadmin.database.ObjectClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "IssuedBook")
public class IssuedBook {
    private Integer bookId,customerId;
    private String issuedDate,returnDate,customerName,bookName,phone;

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public IssuedBook(Integer bookId, Integer customerId, String issuedDate, String returnDate, String customerName, String bookName,String phone) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.issuedDate = issuedDate;
        this.returnDate = returnDate;
        this.customerName = customerName;
        this.bookName = bookName;
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
