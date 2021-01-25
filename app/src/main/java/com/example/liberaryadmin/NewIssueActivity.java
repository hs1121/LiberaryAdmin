package com.example.liberaryadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;

public class NewIssueActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ACTIVITY_TAG=100;
    public static final int CUSTOMER_TAG =10 ;
    public static final int BOOK_TAG =11 ;

    private View v_book,v_customer;
    private DatePicker d_date;
    private Button b_button;
    private ImageView i_back;

    private Customer customer;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_issue);
        LiberaryViewModel.SAR_CALL_TAG=ACTIVITY_TAG;
      //  startActivity(new Intent(getApplicationContext(),CustomerListActivity.class));
        init();
    }

    private void init() {
        v_book=findViewById(R.id.NewIssueActivity_book);
        v_customer=findViewById(R.id.NewIssueActivity_customer);
        d_date=findViewById(R.id.NewIssueActivity_date);
        b_button=findViewById(R.id.NewIssueActivity_button);
        i_back=findViewById(R.id.NewIssueActivity_back);

        v_customer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.NewIssueActivity_customer:
                LiberaryViewModel.SAR_CALL_TAG=ACTIVITY_TAG;
                startActivityForResult(new Intent(getApplicationContext(),CustomerListActivity.class),ACTIVITY_TAG);
                break;
            case R.id.NewIssueActivity_book:
                startActivityForResult(new Intent(getApplicationContext(),BookListActivity.class),ACTIVITY_TAG);
                break;
            case R.id.NewIssueActivity_button:
                break;
            case R.id.NewIssueActivity_back:
                super.onBackPressed();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ACTIVITY_TAG&&resultCode==RESULT_OK&&data!=null){
             customer=data.getParcelableExtra("customer");
             // todo : unable to access the data of views with include meybe need to use data binding for this thing ok . Check it tomorrow
        }
    }
}