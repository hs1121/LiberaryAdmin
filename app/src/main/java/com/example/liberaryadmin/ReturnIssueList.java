package com.example.liberaryadmin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liberaryadmin.Adapters.IssueAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_issue_list);
        init();
        model.getAllIssues().observe(this, list -> {
            this.list=list;
            adapter.getIssueList(list);
            adapter.notifyDataSetChanged();
        });
    }

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
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Confirm Return").setPositiveButton("Yes", (dialog, which) -> {
                model.deleteIssue(issuedBook);
                Toast.makeText(this, "Returned Successfully", Toast.LENGTH_SHORT).show();
            }).setNegativeButton("No",null);
            builder.show();
        });
    }
}