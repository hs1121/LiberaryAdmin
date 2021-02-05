package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Customer;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewCustomerActivity extends AppCompatActivity {

    private TextView t_name, t_address, t_phone, t_membership, t_edit_select;
    private CircleImageView i_image;
    private ImageView b_back;
    private Intent intent;
    private Customer customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
        init();
        if (LiberaryViewModel.SAR_CALL_TAG == NewIssueActivity.ACTIVITY_TAG) {
            t_edit_select.setText("Select");
        }
        intent = getIntent();
        setValues();
    }
    // set customer values to views
    private void setValues() {

        customer = intent.getParcelableExtra("customer");
        t_name.setText(customer.getName());
        t_address.setText(customer.getAddress());
        t_phone.setText(customer.getPhone());
        t_membership.setText(customer.getMembershipEndDate());
        i_image.setImageBitmap(Converter.byteToImage(customer.getImage()));
    }
    //initialises views and variables
    private void init() {
        t_name = findViewById(R.id.ViewCustomer_name);
        t_address = findViewById(R.id.ViewCustomer_address);
        t_phone = findViewById(R.id.ViewCustomer_phone);
        t_edit_select = findViewById(R.id.ViewCustomer_edit_select);
        i_image = findViewById(R.id.ViewCustomer_image);
        t_membership = findViewById(R.id.ViewCustomer_duration);
        b_back = findViewById(R.id.ViewCustomer_back);


        b_back.setOnClickListener(view -> {
                    super.onBackPressed();
                }
        );
        t_edit_select.setOnClickListener(View -> {
            // check which activity called to perform required task
            if (LiberaryViewModel.SAR_CALL_TAG == NewIssueActivity.ACTIVITY_TAG) {
                Intent intent = new Intent();
                intent.putExtra("customer", customer);
                setResult(RESULT_OK, intent);
                LiberaryViewModel.SAR_CALL_TAG = 0;
                finish();
            } else {
                LiberaryViewModel.SAR_CALL_TAG = CustomerListActivity.ACTIVITY_TAG;
                LiberaryViewModel.customer = customer;
                startActivity(new Intent(getApplicationContext(), AddCustomerActivity.class));
                finish();
            }
        });
    }


}