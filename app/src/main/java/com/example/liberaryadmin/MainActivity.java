package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Customer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private CardView cv_books,cv_customer,cv_issues,cv_membership,cv_newIssue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        cv_books=findViewById(R.id.MainActivity_books);
        cv_customer=findViewById(R.id.MainActivity_customers);
        cv_issues=findViewById(R.id.MainActivity_issues);
        cv_membership=findViewById(R.id.MainActivity_membership);
        cv_newIssue=findViewById(R.id.MainActivity_newIssue);

        cv_books.setOnClickListener(this);
        cv_customer.setOnClickListener(this);
        cv_issues.setOnClickListener(this);
        cv_membership.setOnClickListener(this);
        cv_newIssue.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.MainActivity_newIssue:
                startActivity(new Intent(getApplicationContext(),NewIssueActivity.class));
                break;
            case R.id.MainActivity_books:
                startActivity(new Intent(getApplicationContext(),BookListActivity.class));
                break;
            case R.id.MainActivity_customers:
                startActivity(new Intent(getApplicationContext(), CustomerListActivity.class));
                break;
            case R.id.MainActivity_issues:
                break;
            case R.id.MainActivity_membership:
                break;
        }

    }
}
