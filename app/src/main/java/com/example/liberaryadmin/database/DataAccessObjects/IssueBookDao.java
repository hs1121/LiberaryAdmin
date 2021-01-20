package com.example.liberaryadmin.database.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import java.util.List;

@Dao
public interface IssueBookDao {
    @Insert
    void insertNewIssue(IssuedBook issuedBook);
    @Delete
    void deleteIssue(IssuedBook issuedBook);
    @Update
    void updateIssue(IssuedBook issuedBook);
    @Query("select * from IssuedBook order by issuedDate ASC")
    LiveData<List<IssuedBook>> getAllIssues();

}
