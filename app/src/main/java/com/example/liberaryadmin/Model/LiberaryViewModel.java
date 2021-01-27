package com.example.liberaryadmin.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.liberaryadmin.database.DataAccessObjects.BookDao;
import com.example.liberaryadmin.database.DataAccessObjects.CustomerDao;
import com.example.liberaryadmin.database.DataAccessObjects.IssueBookDao;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import java.util.ArrayList;
import java.util.List;

public class LiberaryViewModel extends AndroidViewModel implements BookDao, CustomerDao, IssueBookDao {

    private Repository repositoryInstance;
    private LiveData<List<Book>> bookList;
    private LiveData<List<Customer>> customerList;
    private LiveData<List<IssuedBook>> issueList;
    public static int SAR_CALL_TAG=0; // startActivityforResult
    public static int SAR_ID_TAG=0;
    public static Customer customer;
    public static Book book;

    public LiberaryViewModel(@NonNull Application application){
        super(application);
        repositoryInstance= new Repository(application);
        bookList=repositoryInstance.getAllBooks();
        customerList=repositoryInstance.getAllCustomer();
        issueList=repositoryInstance.getAllIssues();
    }

    public LiveData<List<Customer>> getFilterCustomerList(List<Customer> list,String s){
        List<Customer> list1=new ArrayList<>();
        MutableLiveData<List<Customer>> newList= new MutableLiveData<List<Customer>>();
        for (Customer customer:list) {
            if(customer.getName().contains(s)||customer.getPhone().contains(s)){
               list1.add(customer);
            }
        }
        newList.setValue(list1);
        return  newList;
    }

    public LiveData<List<Book>> getFilterBookList(List<Book> list,String s){
        List<Book> list1=new ArrayList<>();
        MutableLiveData<List<Book>> newList= new MutableLiveData<List<Book>>();
        for (Book book:list) {
            if(book.getName().contains(s)){
                list1.add(book);
            }
        }
        newList.setValue(list1);
        return  newList;
    }
    public LiveData<List<IssuedBook>> getFilterIssuedBookList(List<IssuedBook> list,String s){
        List<IssuedBook> list1=new ArrayList<>();
        MutableLiveData<List<IssuedBook>> newList= new MutableLiveData<List<IssuedBook>>();
        for (IssuedBook issuedBook:list) {
            if(issuedBook.getId().toString().contains(s)||issuedBook.getPhone().contains(s)){
                list1.add(issuedBook);
            }
        }
        newList.setValue(list1);
        return  newList;
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
