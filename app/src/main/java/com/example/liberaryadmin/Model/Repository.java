package com.example.liberaryadmin.Model;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.liberaryadmin.CustomerListActivity;
import com.example.liberaryadmin.database.DataAccessObjects.BookDao;
import com.example.liberaryadmin.database.DataAccessObjects.CustomerDao;
import com.example.liberaryadmin.database.DataAccessObjects.IssueBookDao;
import com.example.liberaryadmin.database.LiberaryDB;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import java.util.List;


public class Repository implements BookDao,CustomerDao,IssueBookDao {

    private static final int INSERT_FLAG=0;
    private static final int DELETE_FLAG=1;
    private static final int UPDATE_FLAG=2;
    private static final int FILTER_FLAG=3;



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
        customerList= customerDao.getAllCustomer();
        issueList=issueBookDao.getAllIssues();
    }
    @Override
    public void insertBook(Book book) {
        new BookAsyncTask(bookDao,INSERT_FLAG).execute(book);
    }

    @Override
    public void deleteBook(Book book) {
        new BookAsyncTask(bookDao,DELETE_FLAG).execute(book);
    }

    @Override
    public void updateBook(Book book) {
        new BookAsyncTask(bookDao,UPDATE_FLAG).execute(book);
    }

    @Override
    public LiveData<List<Book>> getAllBooks() {
        return bookList;
    }

    @Override
    public void insertCustomer(Customer customer) {
            new CustomerAsyncTask(customerDao,INSERT_FLAG).execute(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        new CustomerAsyncTask(customerDao,DELETE_FLAG).execute(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        new CustomerAsyncTask(customerDao,UPDATE_FLAG).execute(customer);
    }

    @Override
    public LiveData<List<Customer>> getAllCustomer() {
        return customerList;
    }


    @Override
    public void insertNewIssue(IssuedBook issuedBook) {
        new IssueAsyncTask(issueBookDao,INSERT_FLAG).execute(issuedBook);

    }

    @Override
    public void deleteIssue(IssuedBook issuedBook) {
        new IssueAsyncTask(issueBookDao,DELETE_FLAG).execute(issuedBook);
    }

    @Override
    public void updateIssue(IssuedBook issuedBook) {
        new IssueAsyncTask(issueBookDao,UPDATE_FLAG).execute(issuedBook);
    }

    @Override
    public LiveData<List<IssuedBook>> getAllIssues() {
        return issueList;
    }


    //////////////////  Async Tasks  /////////////////////////////

    private static class BookAsyncTask extends AsyncTask<Book,Void,Void> {
    BookDao bookDao;
    Integer flag;

    private BookAsyncTask(BookDao bookDao, Integer flag) {
        this.bookDao = bookDao;
        this.flag=flag;
    }

    @Override
    protected Void doInBackground(Book... books) {
        switch (flag){
            case INSERT_FLAG:
                bookDao.insertBook(books[0]);
                break;
            case DELETE_FLAG:
                bookDao.deleteBook(books[0]);
                break;
            case UPDATE_FLAG:
                bookDao.updateBook(books[0]);
                break;
            default:
                break;
        }
        return null;
    }
}

    private static class CustomerAsyncTask extends AsyncTask<Customer,Void,Void> {
        CustomerDao customerDao;
        Integer flag;

        private CustomerAsyncTask(CustomerDao customerDao, Integer flag) {
            this.customerDao = customerDao;
            this.flag = flag;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            switch (flag){
                case INSERT_FLAG:
                    customerDao.insertCustomer(customers[0]);
                    break;
                case DELETE_FLAG:
                    customerDao.deleteCustomer(customers[0]);
                    break;
                case UPDATE_FLAG:
                    customerDao.updateCustomer(customers[0]);
                    break;
                default:
                    break;
            }
            return null;
        }
    }

    private static class IssueAsyncTask extends AsyncTask<IssuedBook,Void,Void> {
        IssueBookDao issueBookDao;
        Integer flag;

        private IssueAsyncTask(IssueBookDao issueBookDao, Integer flag) {
            this.issueBookDao = issueBookDao;
            this.flag = flag;
        }

        @Override
        protected Void doInBackground(IssuedBook... issuedBooks) {
            switch (flag){
                case INSERT_FLAG:
                    issueBookDao.insertNewIssue(issuedBooks[0]);
                    break;
                case DELETE_FLAG:
                    issueBookDao.deleteIssue(issuedBooks[0]);
                    break;
                case UPDATE_FLAG:
                    issueBookDao.updateIssue(issuedBooks[0]);
                    break;
                default:
                    break;
            }
            return null;
        }
    }


}

