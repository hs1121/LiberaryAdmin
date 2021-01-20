package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.liberaryadmin.Adapters.BookAdapter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;

import java.util.List;

public class BookListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        recyclerView=findViewById(R.id.ActivityBookList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BookAdapter adapter=new BookAdapter(this);
        LiberaryViewModel viewModel=new ViewModelProvider(this).get(LiberaryViewModel.class);
        viewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapter.setBookList(books);
                adapter.notifyDataSetChanged();
            }
        });
    }
}