package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewBookActivity extends AppCompatActivity {

    private TextView t_name,t_author,t_year,t_volume,t_editSelect;
    private CircleImageView i_image;
    private ImageView b_back;
    private Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        init();
        setBook();
        // edit select listener
        t_editSelect.setOnClickListener(view->{
            // check which activity called to perform required task
            if(LiberaryViewModel.SAR_CALL_TAG==NewIssueActivity.ACTIVITY_TAG) {
                setResult(RESULT_OK);
                LiberaryViewModel.SAR_CALL_TAG = 0;
            }
            else {
                LiberaryViewModel.SAR_CALL_TAG=BookListActivity.ACTIVITY_TAG;
                startActivity(new Intent(getApplicationContext(), AddBookActivity.class));
            }
            finish();
        });
    }

    // initialise views and variables
    private void init() {
        t_name=findViewById(R.id.ViewBook_name);
        t_author=findViewById(R.id.ViewBook_author);
        t_year=findViewById(R.id.ViewBook_publishYear);
        t_volume=findViewById(R.id.ViewBook_volume);
        i_image=findViewById(R.id.ViewBook_image);
        b_back=findViewById(R.id.ViewBook_back);
        t_editSelect=findViewById(R.id.ViewBook_edit_select);

        b_back.setOnClickListener(view ->{
            super.onBackPressed();
        });

        book= LiberaryViewModel.book;

        if(LiberaryViewModel.SAR_CALL_TAG==NewIssueActivity.ACTIVITY_TAG){
            t_editSelect.setText("Select"); // change text from edit to select
        }
    }
    //sets book data to views
    private void setBook() {
        t_name.setText(book.getName());
        t_author.setText(book.getAuthor());
        t_year.setText(book.getPublishYear());
        t_volume.setText("Volume : "+book.getVolume());
        i_image.setImageBitmap(Converter.byteToImage(book.getImage()));
    }
}