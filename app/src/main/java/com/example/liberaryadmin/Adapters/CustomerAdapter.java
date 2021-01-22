package com.example.liberaryadmin.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liberaryadmin.Converter;
import com.example.liberaryadmin.CustomDate;
import com.example.liberaryadmin.R;
import com.example.liberaryadmin.database.ObjectClasses.Book;
import com.example.liberaryadmin.database.ObjectClasses.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private List<Customer> customerList=new ArrayList<>();

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }


    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_layout,parent,false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
                Customer customer=customerList.get(position);
                holder.name.setText(customer.getName());
                //TODO add a if statement to change membershipEndDate form membership till to expired on
                holder.regDate.setText("Reg. Date : "+customer.getRegisteredDate());
                    holder.membershipEndDate.setText("Membership till : "+customer.getMembershipEndDate());
                holder.image.setImageBitmap(Converter.byteToImage(customer.getImage()));
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        private TextView name,regDate,membershipEndDate;
        private ImageView image;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CustomerList_CustomerName);
            regDate=itemView.findViewById(R.id.CustomerList_regDate);
            membershipEndDate=itemView.findViewById(R.id.CustomerList_membershipEndDate);
            image=itemView.findViewById(R.id.CustomerList_image);
        }
    }
}
