package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import com.example.liberaryadmin.Adapters.CustomerAdapter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Customer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LiberaryViewModel viewModel;
    private CustomerAdapter adapter;
    private FloatingActionButton b_addButton;
    private EditText et_search;
    private List<Customer> customerList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        init();
        viewModel.getAllCustomer().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> Customers) {
                customerList=Customers;
                adapter.setCustomerList(Customers);
                adapter.notifyDataSetChanged();

            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                   adapter.filterList(s.toString(),customerList);
            }
        });
    }



    private void init() {
        b_addButton=findViewById(R.id.ActivityCustomerList_add);
        et_search=findViewById(R.id.ActivityCustomerList_search);
        recyclerView=findViewById(R.id.ActivityCustomerList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CustomerAdapter();
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this).get(LiberaryViewModel.class);

        b_addButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),AddCustomerActivity.class));
        });
    }
}