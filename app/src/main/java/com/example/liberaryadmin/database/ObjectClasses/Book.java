package com.example.liberaryadmin.database.ObjectClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;

@Entity(tableName = "Book")
public class Book {
    private String  name,author,publishYear,volume;
    private Integer quantity;
    private byte[] image;
    @PrimaryKey(autoGenerate = true)
    private Integer id=0;

    public void setId(Integer id) {
        this.id = id;
    }

    public Book(String name, String author, String publishYear, Integer quantity,String volume,byte[] image) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.quantity = quantity;
        this.volume= volume;
        this.image= image;
    }

    public byte[]getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }
}
