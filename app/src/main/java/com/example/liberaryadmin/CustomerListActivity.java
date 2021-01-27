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

    private void init() {
        b_addButton=findViewById(R.id.ActivityCustomerList_add);
        i_back=findViewById(R.id.ActivityCustomerList_back);
        et_search=findViewById(R.id.ActivityCustomerList_search);
        recyclerView=findViewById(R.id.ActivityCustomerList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CustomerAdapter();
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this).get(LiberaryViewModel.class);

        b_addButton.setOnClickListener(v -> {
            if(LiberaryViewModel.SAR_CALL_TAG==NewIssueActivity.ACTIVITY_TAG){
                startActivityForResult(new Intent(getApplicationContext(), AddCustomerActivity.class),NewIssueActivity.ACTIVITY_TAG);
            }
            else {
                startActivity(new Intent(getApplicationContext(), AddCustomerActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.getFilterCustomerList(customerList,s.toString()).observe(CustomerListActivity.this, new Observer<List<Customer>>() {
                    @Override
                    public void onChanged(List<Customer> list) {
                        adapter.setCustomerList(list);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        adapter.setOnCustomerClickListner(new CustomerAdapter.OnCostomerClickListner() {
            @Override
            public void onClick(Customer customer) {
                Intent intent=new Intent(getApplicationContext(),ViewCustomerActivity.class);
                intent.putExtra("customer",customer);
                startActivityForResult(intent,NewIssueActivity.ACTIVITY_TAG);
            }

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