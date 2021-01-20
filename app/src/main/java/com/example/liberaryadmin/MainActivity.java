package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.liberaryadmin.Model.LiberaryViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

    }

    public void check1(View view) {
        startActivity(new Intent(getApplicationContext(),AddBookActivity.class));
    }
    public void check2(View view) {
        startActivity(new Intent(getApplicationContext(),BookListActivity.class));
    }
}