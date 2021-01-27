package com.example.liberaryadmin.Adapters;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.R;
import com.example.liberaryadmin.database.ObjectClasses.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {


    private static final String ETAG = "Adapter Book error";
    private List<Book> bookList=new ArrayList<>();
    private OnBookClickListner onBookClickListner;


    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_layout,parent,false);
        return new BookViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull BookAdapter.BookViewHolder holder, int position) {
        Book book=bookList.get(position);
        holder.name.setText(book.getName());
        holder.author.setText("~By "+book.getAuthor());
        holder.volume.setText(book.getVolume());
        holder.quantity.setText("Qnt. "+book.getQuantity().toString());
        try {
            Bitmap bitmap= Converter.byteToImage(book.getImage());
            holder.image.setImageBitmap(bitmap);
        }
        catch(Exception e){
            Log.e(ETAG,e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private TextView name,author,volume,quantity;
        private ImageView image;

        public BookViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.BookList_bookName);
            author=itemView.findViewById(R.id.BookList_authorName);
            volume=itemView.findViewById(R.id.BookList_volume);
            quantity=itemView.findViewById(R.id.BookList_quantity);
            image=itemView.findViewById(R.id.BookList_image);
            itemView.setOnClickListener(view->{
                onBookClickListner.onClick(bookList.get(getAdapterPosition()));
            });
        }
    }
    public interface OnBookClickListner{
        void onClick(Book book);
    }
    public void setOnBookClickListner(OnBookClickListner onBookClickListner){
        this.onBookClickListner=onBookClickListner;
    }
}
