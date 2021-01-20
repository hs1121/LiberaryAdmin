package com.example.liberaryadmin.database.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.liberaryadmin.database.ObjectClasses.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Insert
    void insertCustomer(Customer customer);
    @Delete
    void deleteCustomer(Customer customer);
    @Update
    void updateCustomer(Customer customer);
    @Query("select * from Customer order by name ASC")
    LiveData<List<Customer>> getAllCustomer();
}
