package com.example.liberaryadmin.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.liberaryadmin.database.DataAccessObjects.BookDao;
import com.example.liberaryadmin.database.DataAccessObjects.CustomerDao;
import com.example.liberaryadmin.database.DataAccessObjects.IssueBookDao;
import com.example.liberaryadmin.database.LiberaryDB;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import java.util.List;


public class Repository implements BookDao,CustomerDao,IssueBookDao {

    private BookDao bookDao;
    private CustomerDao customerDao;
    private IssueBookDao issueBookDao;

    private LiveData<List<Book>> bookList;
    private LiveData<List<Customer>> customerList;
    private LiveData<List<IssuedBook>> issueList;

    public Repository(Application application){
        LiberaryDB liberaryDB=LiberaryDB.getInstance(application);
        bookDao=liberaryDB.bookDao();
        customerDao=liberaryDB.customerDao();
        issueBookDao=liberaryDB.issueBookDao();

        bookList=bookDao.getAllBooks();
        customerList=customerDao.getAllCustomer();
        issueList=issueBookDao.getAllIssues();
    }




    @Override
    public void insertBook(Book book) {
      //  bookDao.insertBook(book);
        new InsertNoteAsyncTask(bookDao).execute(book);
    }

    @Override
    public void deleteBook(Book book) {
            bookDao.deleteBook(book);
    }

    @Override
    public void updateBook(Book book) {
            bookDao.updateBook(book);
    }

    @Override
    public LiveData<List<Book>> getAllBooks() {
        return bookList;
    }

    @Override
    public void insertCustomer(Customer customer) {
            customerDao.insertCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
            customerDao.deleteCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
            customerDao.updateCustomer(customer);
    }

    @Override
    public LiveData<List<Customer>> getAllCustomer() {
        return customerList;
    }


    @Override
    public void insertNewIssue(IssuedBook issuedBook) {
        issueBookDao.insertNewIssue(issuedBook);

    }

    @Override
    public void deleteIssue(IssuedBook issuedBook) {
            issueBookDao.deleteIssue(issuedBook);
    }

    @Override
    public void updateIssue(IssuedBook issuedBook) {
            issueBookDao.updateIssue(issuedBook);
    }

    @Override
    public LiveData<List<IssuedBook>> getAllIssues() {
        return issueList;
    }

private static class InsertNoteAsyncTask extends AsyncTask<Book,Void,Void> {
    BookDao noteDao;

    private InsertNoteAsyncTask(BookDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Book... notes) {
        noteDao.insertBook(notes[0]);
        return null;
    }
}
}

