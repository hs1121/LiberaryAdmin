 package com.example.liberaryadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.Helpers.CustomDate;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import de.hdodenhof.circleimageview.CircleImageView;

 public class NewIssueActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ACTIVITY_TAG=100;
    public static final int CUSTOMER_TAG =10 ;
    public static final int BOOK_TAG =11 ;

    private DatePicker d_date;
    private Button b_button;
    private ImageView i_back;
    private LinearLayout linearLayout;

    private TextView t_customerName,t_bookName,t_registerDate,t_membershipEndDate,t_authorName,t_bookVolume;
    private CircleImageView i_bookImage,i_customerImage;
    private CardView cv_customer,cv_book;

    private Customer customer;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_issue);
        LiberaryViewModel.SAR_CALL_TAG=ACTIVITY_TAG;
        init();
        configDate();

    }

     private void configDate() {
        d_date.setMinDate(System.currentTimeMillis()+86400000);
        d_date.setMaxDate(System.currentTimeMillis()+7*86400000);
     }

     private void init() {

        d_date=findViewById(R.id.NewIssueActivity_date);
        b_button=findViewById(R.id.NewIssueActivity_button);
        i_back=findViewById(R.id.NewIssueActivity_back);
        t_customerName=findViewById(R.id.NewIssueActivity_CustomerName);
        t_bookName=findViewById(R.id.NewIssueActivity_bookName);
        t_registerDate=findViewById(R.id.NewIssueActivity_regDate);
        t_membershipEndDate=findViewById(R.id.NewIssueActivity_membershipEndDate);
        t_authorName=findViewById(R.id.NewIssueActivity_authorName);
        t_bookVolume=findViewById(R.id.NewIssueActivity_bookVolume);
        cv_customer=findViewById(R.id.NewIssueActivity_customer);
        cv_book=findViewById(R.id.NewIssueActivity_book);
        i_customerImage=findViewById(R.id.NewIssueActivity_CustomerImage);
        i_bookImage=findViewById(R.id.NewIssueActivity_bookImage);
        linearLayout=findViewById(R.id.NewIssueActivity_linearLayout);

        cv_customer.setOnClickListener(this);
        cv_book.setOnClickListener(this);
        b_button.setOnClickListener(this);
        i_back.setOnClickListener(this);

        customer=LiberaryViewModel.customer;
        book=LiberaryViewModel.book;

    }

     @Override
     protected void onStart() {
         super.onStart();
         if(customer!=null)
             setCustomerData();
         if(book!=null)
             setBookData();
     }

     @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.NewIssueActivity_customer:
                LiberaryViewModel.SAR_CALL_TAG=ACTIVITY_TAG;
                startActivityForResult(new Intent(getApplicationContext(),CustomerListActivity.class),CUSTOMER_TAG);
                break;
            case R.id.NewIssueActivity_book:
                LiberaryViewModel.SAR_CALL_TAG=ACTIVITY_TAG;
                startActivityForResult(new Intent(getApplicationContext(),BookListActivity.class),BOOK_TAG);

                break;
            case R.id.NewIssueActivity_button:
                check();
                break;
            case R.id.NewIssueActivity_back:
                super.onBackPressed();
                break;
        }
    }

     private void check() {
        if(customer==null){
            Toast.makeText(this, "Select Customer", Toast.LENGTH_SHORT).show();
            t_customerName.setError("Select Customer");
        }
        else if(book==null){
            Toast.makeText(this, "Select Book", Toast.LENGTH_SHORT).show();
            t_bookName.setError("Select Book");
        }
        else{
            uploadIssue();
        }
     }

     private void uploadIssue() {
        LiberaryViewModel model=new ViewModelProvider(this).get(LiberaryViewModel.class);
        String date;
        int month;
        month=d_date.getMonth()+1;
        date=d_date.getDayOfMonth()+"-"+month+"-"+d_date.getYear();
         IssuedBook issuedBook=new IssuedBook(book.getId(),customer.getId()
                 ,new CustomDate(System.currentTimeMillis()).dateToString(),date,customer.getName(),book.getName(),customer.getPhone());
        model.insertNewIssue(issuedBook);
        screenShot();
     }

     private void screenShot() {
             View v = linearLayout;
             v.setBackground(getDrawable(R.drawable.gradient_background));
             v.setDrawingCacheEnabled(true);
         AlertDialog.Builder dialog=new AlertDialog.Builder(this,R.style.newIssueDialog);
         View itemView=getLayoutInflater().inflate(R.layout.snapshot_dialog_layout,null);
         dialog.setView(itemView).show();
         ImageView imageView=itemView.findViewById(R.id.Dialog_snapshot);
         Button button=itemView.findViewById(R.id.Dialog_okButton);

            Bitmap bitmap=Bitmap.createBitmap(v.getDrawingCache());
            if(bitmap!=null)
            imageView.setImageBitmap(bitmap);

         button.setOnClickListener(view->{
             Toast.makeText(this, "issue uploaded", Toast.LENGTH_SHORT).show();
             onBackPressed();
         });

     }


     @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CUSTOMER_TAG&&resultCode==RESULT_OK&&data!=null){
             customer=data.getParcelableExtra("customer");
             LiberaryViewModel.customer=customer;
                setCustomerData();
        }
        else if(requestCode==BOOK_TAG&&resultCode==RESULT_OK){
             book=LiberaryViewModel.book;
             setBookData();
         }
    }

     private void setCustomerData() {
         t_customerName.setText(customer.getName());
         Bitmap bitmap = Converter.byteToImage(customer.getImage());
         i_customerImage.setImageBitmap(bitmap);

         t_registerDate.setText("Reg. Date : " + customer.getRegisteredDate());
         if (CustomDate.isLess(CustomDate.stringToDate(customer.getMembershipEndDate()), new CustomDate(System.currentTimeMillis()))) {
             t_membershipEndDate.setText("Membership expired on : " + customer.getMembershipEndDate());
         } else {
             t_membershipEndDate.setText("Membership till : " + customer.getMembershipEndDate());
         }

     }
     private void setBookData() {
        t_bookName.setText(book.getName());
        t_authorName.setText(book.getAuthor());
        t_bookVolume.setText("Volume : "+book.getVolume());
        i_bookImage.setImageBitmap(Converter.byteToImage(book.getImage()));
     }

 }