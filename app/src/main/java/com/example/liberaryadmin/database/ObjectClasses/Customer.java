package com.example.liberaryadmin.database.ObjectClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Customer")
public class Customer {
    private String name,phone;
    private String registeredDate,membershipStartDate,membershipEndDate;
    @PrimaryKey(autoGenerate = true)
    private Integer id=0;

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer(String name, String phone, String registeredDate, String membershipStartDate, String membershipEndDate) {
        this.name = name;
        this.phone = phone;
        this.registeredDate = registeredDate;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
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
