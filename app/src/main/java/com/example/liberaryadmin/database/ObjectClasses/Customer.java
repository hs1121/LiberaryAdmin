package com.example.liberaryadmin.database.ObjectClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Customer")
public class Customer {
    private String name,phone,address;
    private String registeredDate,membershipStartDate,membershipEndDate;
    private byte[] image;
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer(String name, String phone, String address, String registeredDate, String membershipStartDate, String membershipEndDate,byte[] image) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.registeredDate = registeredDate;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
        this.image=image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate( String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate( String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public String getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate( String membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public Integer getId() {
        return id;
    }

}
