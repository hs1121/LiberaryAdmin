package com.example.liberaryadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;


import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;

import java.io.ByteArrayOutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView t_name,t_author,t_year,t_volume;
    private CircleImageView i_image;
    private Button b_button;

    private LiberaryViewModel viewModelInstance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        init();
    }

    private void init() {
        t_name=findViewById(R.id.AddBook_name);
        t_author=findViewById(R.id.AddBook_author);
        t_year=findViewById(R.id.AddBook_publishYear);
        t_volume=findViewById(R.id.AddBook_volume);
        i_image=findViewById(R.id.AddBook_selectImage);
        b_button=findViewById(R.id.AddBook_addButton);
        viewModelInstance=new ViewModelProvider(this).get(LiberaryViewModel.class);

        b_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.AddBook_addButton:
                addBook();
        }
    }

    private void addBook() {
        String name,author,volume="Volume : ",year;
        name=t_name.getText().toString();
        author=t_author.getText().toString();
        volume+=t_volume.getText().toString();
        year=t_year.getText().toString();


        Bitmap bmp = ((BitmapDrawable)i_image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        Book book=new Book(name,author,year,1,volume,image);
        viewModelInstance.insertBook(book);
    }
}