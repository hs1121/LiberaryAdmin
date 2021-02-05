package com.example.liberaryadmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liberaryadmin.Adapters.IssueAdapter;
import com.example.liberaryadmin.Helpers.CustomDate;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReturnIssueList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CircleImageView i_back;
    private TextView t_search;

    private LiberaryViewModel model;
    private IssueAdapter adapter;

    private List<IssuedBook> list;
    IssuedBook issuedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_issue_list);
        init();
        // observes list and notifies adapter
        model.getAllIssues().observe(this, list -> {
            this.list = list;
            adapter.setIssueList(list);
            adapter.notifyDataSetChanged();
        });

        // listens if something is typed in search bar
        t_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    // observes the change in filtered list when text in search bar is changed
                    model.getFilterIssuedBookList(list,s.toString()).observe(ReturnIssueList.this, list -> {
                        adapter.setIssueList(list);
                        adapter.notifyDataSetChanged();
                    });
            }
        });

    }

    // initialises views and variables
    private void init() {
        recyclerView=findViewById(R.id.ActivityReturnIssueList_recyclerView);
        i_back=findViewById(R.id.ActivityReturnIssueList_back);
        t_search=findViewById(R.id.ActivityReturnIssueList_search);

        model=new ViewModelProvider(this).get(LiberaryViewModel.class);
        adapter=new IssueAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        i_back.setOnClickListener(view->{
            super.onBackPressed();
        });
        adapter.setOnIssueClickListner(issuedBook->{
            this.issuedBook=issuedBook;
            createDialog();
        });

    }

    // create dialog for conformation of return and also tells the penalty
    private void createDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this,R.style.MyDialogStyle);
        View itemView=getLayoutInflater().inflate(R.layout.return_book_dialog_layout,null);
        dialog.setView(itemView);
        TextView t_bookName,t_customerName,t_phone,t_id,t_penalty;
        Button b_cancel,b_return;

        t_bookName=itemView.findViewById(R.id.Dialog_bookName);
        t_customerName=itemView.findViewById(R.id.Dialog_CustomerName);
        t_phone=itemView.findViewById(R.id.Dialog_phone);
        t_id=itemView.findViewById(R.id.Dialog_Id);
        b_cancel=itemView.findViewById(R.id.Dialog_cancel);
        b_return=itemView.findViewById(R.id.Dialog_return);
        t_penalty=itemView.findViewById(R.id.Dialog_penalty);


        t_bookName.setText("Book : "+issuedBook.getBookName());
        t_customerName.setText("Customer :"+issuedBook.getCustomerName());
        t_phone.setText("Phone : "+issuedBook.getPhone());
        t_id.setText("Issue Id : "+issuedBook.getId().toString());
        int difference=CustomDate.difference(new CustomDate(System.currentTimeMillis()).dateToString(),issuedBook.getReturnDate());
        if(difference>0){
            t_penalty.setVisibility(View.VISIBLE);
            t_penalty.setText("PENALTY : "+difference*50);
        }

        final AlertDialog ad=dialog.show();

        b_cancel.setOnClickListener(view->{
            ad.dismiss();;
        });
        b_return.setOnClickListener(view->{

            model.deleteIssue(issuedBook);
            Toast.makeText(this, "Issue Returned", Toast.LENGTH_SHORT).show();
            ad.dismiss();
        });


    }
}