 package com.example.liberaryadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;


import com.example.liberaryadmin.Adapters.CustomerAdapter;
import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

 public class CustomerListActivity extends AppCompatActivity {

    public static final int ACTIVITY_TAG=104;

    private RecyclerView recyclerView;
    private LiberaryViewModel viewModel;
    private CustomerAdapter adapter;
    private FloatingActionButton b_addButton;
    private EditText et_search;
    private CircleImageView i_back;
    private List<Customer> customerList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        init();
        viewModel.getAllCustomer().observe(this, Customers -> {
            customerList=Customers;
            adapter.setCustomerList(Customers);
            adapter.notifyDataSetChanged();
        });
        i_back.setOnClickListener(view ->{
            super.onBackPressed();
        });
    }
    // Initialises views and variables
    private void init() {
        b_addButton=findViewById(R.id.ActivityCustomerList_add);
        i_back=findViewById(R.id.ActivityCustomerList_back);
        et_search=findViewById(R.id.ActivityCustomerList_search);
        recyclerView=findViewById(R.id.ActivityCustomerList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CustomerAdapter();
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this).get(LiberaryViewModel.class);

        // check if this activity is called by NewIssueActivity or not
        b_addButton.setOnClickListener(v -> {
            if(LiberaryViewModel.SAR_CALL_TAG==NewIssueActivity.ACTIVITY_TAG){
                startActivityForResult(new Intent(getApplicationContext(), AddCustomerActivity.class),NewIssueActivity.ACTIVITY_TAG);
            }
            else {
                startActivity(new Intent(getApplicationContext(), AddCustomerActivity.class));
            }
        });
        // removes add button when called by expiredMemberActivity .(No need to add customer in expires list )
        if(LiberaryViewModel.SAR_CALL_TAG==MainActivity.EXPIRED_MEMBER_TAG)
            b_addButton.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Listens change in text in search bar
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // observes changes in filtered list
                viewModel.getFilterCustomerList(customerList,s.toString()).observe(CustomerListActivity.this, list -> {
                    adapter.setCustomerList(list);
                    adapter.notifyDataSetChanged();
                });
            }
        });
        adapter.setOnCustomerClickListner(customer -> {
            Intent intent=new Intent(getApplicationContext(),ViewCustomerActivity.class);
            intent.putExtra("customer",customer);  // just used parsable for practice nothing else
            startActivityForResult(intent,NewIssueActivity.ACTIVITY_TAG);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NewIssueActivity.ACTIVITY_TAG&&resultCode==RESULT_OK&&data!=null){
            Intent intent=new Intent();
            Customer customer=data.getParcelableExtra("customer");
            intent.putExtra("customer",customer);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}