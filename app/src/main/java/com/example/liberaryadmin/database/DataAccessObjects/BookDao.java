package com.example.liberaryadmin.database.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.liberaryadmin.database.ObjectClasses.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insertBook(Book book);

    @Delete
    void deleteBook(Book book);

    @Update
    void updateBook(Book book);

    @Query("select * from Book order by id asc")
    LiveData<List<Book>> getAllBooks();

}
