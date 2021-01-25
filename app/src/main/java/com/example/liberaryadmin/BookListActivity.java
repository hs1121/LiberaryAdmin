package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.liberaryadmin.Adapters.BookAdapter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookListActivity extends AppCompatActivity {

    public static final int ACTIVITY_TAG=103;

    private RecyclerView recyclerView;
    private LiberaryViewModel viewModel;
    private BookAdapter adapter;
    private FloatingActionButton b_addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        init();
        viewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
               adapter.setBookList(books);
               adapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        b_addButton=findViewById(R.id.ActivityBookList_add);
        recyclerView=findViewById(R.id.ActivityBookList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter=new BookAdapter();
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this).get(LiberaryViewModel.class);

        b_addButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),AddBookActivity.class));
        });
    }
}