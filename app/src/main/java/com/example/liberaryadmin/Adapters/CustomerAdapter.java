package com.example.liberaryadmin.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.Helpers.CustomDate;
import com.example.liberaryadmin.R;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
                Customer customer=customerList.get(position);
                holder.name.setText(customer.getName());
        holder.regDate.setText("Reg. Date : "+customer.getRegisteredDate());
        holder.image.setImageBitmap(Converter.byteToImage(customer.getImage()));
                //TODO add a if statement to change membershipEndDate form membership till to expired on
        if(CustomDate.isLess(CustomDate.stringToDate(customer.getMembershipEndDate()),new CustomDate(System.currentTimeMillis()))){
            holder.membershipEndDate.setText("Membership expired on : "+customer.getMembershipEndDate());
        }
        else {
            holder.membershipEndDate.setText("Membership till : " + customer.getMembershipEndDate());
        }
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void filterList(String s,List<Customer> customers) {
        List<Customer> list=new ArrayList<>();
        for (Customer customer:customers) {
            if(customer.getName().contains(s)){
                list.add(customer);
            }
        }
        customerList=list;
        notifyDataSetChanged();
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
