package com.example.liberaryadmin.database.ObjectClasses;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Customer")
public class Customer implements Parcelable {
    private String name,phone,address;
    private String registeredDate,membershipStartDate,membershipEndDate;
    private byte[] image;
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    protected Customer(Parcel in) {
        name = in.readString();
        phone = in.readString();
        address = in.readString();
        registeredDate = in.readString();
        membershipStartDate = in.readString();
        membershipEndDate = in.readString();
        image = in.createByteArray();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(registeredDate);
        dest.writeString(membershipStartDate);
        dest.writeString(membershipEndDate);
        dest.writeByteArray(image);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
    }
}
