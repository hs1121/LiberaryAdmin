package com.example.liberaryadmin.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liberaryadmin.R;
import com.example.liberaryadmin.database.ObjectClasses.IssuedBook;

import java.util.ArrayList;
import java.util.List;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {
    private List<IssuedBook> list=new ArrayList<>();
    private OnIssueClickListner issueClickListner;

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_list_layout,parent,false);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder holder, int position) {
            IssuedBook issue=list.get(position);
            holder.t_id.setText("ID : "+issue.getId());
        holder.t_customerName.setText("Customer : "+issue.getCustomerName());
        holder.t_bookName.setText("Book : "+issue.getBookName());
        holder.t_issuedDate.setText(issue.getIssuedDate());
        holder.t_returnDate.setText(issue.getReturnDate());
        holder.t_phone.setText("Phone : "+issue.getPhone());

    }

    public void setIssueList(List<IssuedBook> list){
        this.list=list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class IssueViewHolder extends RecyclerView.ViewHolder {
        private TextView t_id,t_bookName,t_customerName,t_issuedDate,t_returnDate,t_phone;
        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);
            t_id=itemView.findViewById(R.id.IssueList_id);
            t_bookName=itemView.findViewById(R.id.IssueList_bookName);
            t_customerName=itemView.findViewById(R.id.IssueList_customerName);
            t_issuedDate=itemView.findViewById(R.id.IssueList_issueDate);
            t_returnDate=itemView.findViewById(R.id.IssueList_returnDate);
            t_phone=itemView.findViewById(R.id.IssueList_phone);
            itemView.setOnClickListener(view->{
                issueClickListner.onClick(list.get(getAdapterPosition()));
            });
        }
    }
    public interface OnIssueClickListner{
        void onClick(IssuedBook issuedBook);
    }
    public void setOnIssueClickListner(OnIssueClickListner issueClickListner){
        this.issueClickListner=issueClickListner;
    }
}
