package com.example.liberaryadmin.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.liberaryadmin.database.DataAccessObjects.BookDao;
import com.example.liberaryadmin.database.DataAccessObjects.CustomerDao;
import com.example.liberaryadmin.database.DataAccessObjects.IssueBookDao;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

@Database(entities = {Book.class, Customer.class, IssuedBook.class},version = 1, exportSchema = false)
public abstract class LiberaryDB extends RoomDatabase {
    public static LiberaryDB dbInstance;
    public abstract IssueBookDao issueBookDao();
    public abstract BookDao bookDao();
    public abstract CustomerDao customerDao();

    public static synchronized LiberaryDB getInstance(Context context){
        if(dbInstance==null){
            dbInstance= Room.databaseBuilder(context.getApplicationContext(),LiberaryDB.class,"Liberary Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
