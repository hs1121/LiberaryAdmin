package com.example.liberaryadmin.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.AndroidViewModel;


import com.example.liberaryadmin.database.DataAccessObjects.BookDao;
import com.example.liberaryadmin.database.DataAccessObjects.CustomerDao;
import com.example.liberaryadmin.database.DataAccessObjects.IssueBookDao;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import java.util.List;

public class LiberaryViewModel extends AndroidViewModel implements BookDao, CustomerDao, IssueBookDao {

    private Repository repositoryInstance;
    private LiveData<List<Book>> bookList;
    private LiveData<List<Customer>> customerList;
    private LiveData<List<IssuedBook>> issueList;

    public LiberaryViewModel(@NonNull Application application){
        super(application);
        repositoryInstance= new Repository(application);
        bookList=repositoryInstance.getAllBooks();
        customerList=repositoryInstance.getAllCustomer();
        issueList=repositoryInstance.getAllIssues();
    }


    @Override
    public void insertBook(Book book) {
        repositoryInstance.insertBook(book);
    }

    @Override
    public void deleteBook(Book book) {
repositoryInstance.deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
        repositoryInstance.updateBook(book);
    }

    @Override
    public LiveData<List<Book>> getAllBooks() {
        return  bookList;
    }

    @Override
    public void insertCustomer(Customer customer) {
                repositoryInstance.insertCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
            repositoryInstance.deleteCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
            repositoryInstance.updateCustomer(customer);
    }

    @Override
    public LiveData<List<Customer>> getAllCustomer() {
        return customerList;
    }

    @Override
    public void insertNewIssue(IssuedBook issuedBook) {
            repositoryInstance.insertNewIssue(issuedBook);
    }

    @Override
    public void deleteIssue(IssuedBook issuedBook) {
            repositoryInstance.deleteIssue(issuedBook);
    }

    @Override
    public void updateIssue(IssuedBook issuedBook) {
            repositoryInstance.updateIssue(issuedBook);
    }

    @Override
    public LiveData<List<IssuedBook>> getAllIssues() {
        return issueList;
    }
}
