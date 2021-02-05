package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Customer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private CardView cv_books,cv_customer,cv_returnIssue,cv_membership,cv_newIssue;
   public static final int EXPIRED_MEMBER_TAG=5;

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override // resets all values in LibraryViewModel class to prevent false calling or false data
    protected void onResume() {
        super.onResume();
        LiberaryViewModel.SAR_CALL_TAG=0;
        LiberaryViewModel.book=null;
        LiberaryViewModel.customer=null;
    }
    //Initialises views and variables
    private void init() {
        cv_books=findViewById(R.id.MainActivity_books);
        cv_customer=findViewById(R.id.MainActivity_customers);
        cv_returnIssue=findViewById(R.id.MainActivity_returnIssue);
        cv_membership=findViewById(R.id.MainActivity_expiredMembers);
        cv_newIssue=findViewById(R.id.MainActivity_newIssue);

        cv_books.setOnClickListener(this);
        cv_customer.setOnClickListener(this);
        cv_returnIssue.setOnClickListener(this);
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
            case R.id.MainActivity_returnIssue:
                startActivity(new Intent(getApplicationContext(),ReturnIssueList.class));
                break;
            case R.id.MainActivity_expiredMembers:
                LiberaryViewModel.SAR_CALL_TAG=EXPIRED_MEMBER_TAG; // to tell that which activity has called intent
                startActivity(new Intent(getApplicationContext(), CustomerListActivity.class));
                break;
        }

    }


    @Override  // double press back to exit  function
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
}
