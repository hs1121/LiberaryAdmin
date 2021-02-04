package com.example.liberaryadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Book;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int IMG_TAG = 1;
    private TextView t_name,t_author,t_year,t_volume;
    private CircleImageView i_image;
    private ImageView b_back;
    private Button b_button;
    private CardView cv_selectImage;
    private Book book;

    private LiberaryViewModel viewModelInstance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        init();
        if(LiberaryViewModel.SAR_CALL_TAG==BookListActivity.ACTIVITY_TAG){
            book=LiberaryViewModel.book;
            t_name.setText(book.getName());
            t_author.setText(book.getAuthor());
            t_year.setText(book.getPublishYear());
            t_volume.setText(book.getVolume());
            i_image.setImageBitmap(Converter.byteToImage(book.getImage()));
        }
    }

    private void init() {
        t_name=findViewById(R.id.AddBook_name);
        t_author=findViewById(R.id.AddBook_author);
        t_year=findViewById(R.id.AddBook_publishYear);
        t_volume=findViewById(R.id.AddBook_volume);
        i_image=findViewById(R.id.AddBook_image);
        b_button=findViewById(R.id.AddBook_addButton);
        cv_selectImage=findViewById(R.id.AddBook_addImage);
        b_back=findViewById(R.id.AddBook_back);
        viewModelInstance=new ViewModelProvider(this).get(LiberaryViewModel.class);

        b_button.setOnClickListener(this);
        cv_selectImage.setOnClickListener(this);
        b_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.AddBook_addButton:
                check();
                break;
            case R.id.AddBook_addImage:
                addImage();
                break;
            case R.id.AddBook_back:
                super.onBackPressed();
                break;
        }
    }

    private void check() {
        if(t_name.getText().toString().trim().isEmpty()){
            t_name.setError("Enter Book name");
        }
        else if(t_author.getText().toString().trim().isEmpty()){
            t_author.setError("Enter author name");
        }
        else if(t_volume.getText().toString().trim().isEmpty()){
            t_author.setError("Enter volume");
        }
        else if(t_year.getText().toString().trim().isEmpty() ||
                Integer.parseInt(t_year.getText().toString())>  Calendar.getInstance().get(Calendar.YEAR)){
            t_year.setError("Enter valid year");
        }
        else
        addBook();
    }

    private void addImage() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,IMG_TAG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_TAG&&resultCode==RESULT_OK){
            Uri uri=data.getData();
            i_image.setImageURI(uri);
        }
    }

    private void addBook() {
        String name,author,volume,year;
        name=t_name.getText().toString();
        author=t_author.getText().toString();
        volume=t_volume.getText().toString();
        year=t_year.getText().toString();
        Bitmap bmp = ((BitmapDrawable)i_image.getDrawable()).getBitmap();
        byte[] image = Converter.imageToByte(bmp);
        if(image.length>150000){
            Toast.makeText(this, "Image size too big", Toast.LENGTH_LONG).show();
        }else {
            Book book = new Book(name, author, year, 1, volume, image);
            if(LiberaryViewModel.SAR_CALL_TAG==BookListActivity.ACTIVITY_TAG){
                book.setId(this.book.getId());
                viewModelInstance.updateBook(book);
                Toast.makeText(this, "Book updated", Toast.LENGTH_SHORT).show();
            }
            else {
                viewModelInstance.insertBook(book);
                Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
            }
            super.onBackPressed();
        }
    }
}