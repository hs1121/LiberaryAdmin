package com.example.liberaryadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liberaryadmin.Adapters.BookAdapter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookListActivity extends AppCompatActivity {

    public static final int ACTIVITY_TAG=103;

    private RecyclerView recyclerView;
    private LiberaryViewModel viewModel;
    private BookAdapter adapter;
    private FloatingActionButton b_addButton;
    private ImageView i_back;
    private TextView t_search;

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
        i_back=findViewById(R.id.ActivityBookList_back);
        recyclerView=findViewById(R.id.ActivityBookList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter=new BookAdapter();
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this).get(LiberaryViewModel.class);

        i_back.setOnClickListener(view->{
            super.onBackPressed();
        });
        b_addButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),AddBookActivity.class));
        });
        adapter.setOnBookClickListner(book -> {
                if(LiberaryViewModel.SAR_CALL_TAG==NewIssueActivity.ACTIVITY_TAG){
                    LiberaryViewModel.book=book;
                    startActivityForResult(new Intent(getApplicationContext(),ViewBookActivity.class),NewIssueActivity.ACTIVITY_TAG);
                }
                else{
                    startActivity(new Intent(getApplicationContext(),ViewBookActivity.class));
                }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NewIssueActivity.ACTIVITY_TAG&&resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }
}