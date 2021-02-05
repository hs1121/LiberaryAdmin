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
import com.example.liberaryadmin.MainActivity;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.NewIssueActivity;
import com.example.liberaryadmin.R;
import com.example.liberaryadmin.database.ObjectClasses.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private List<Customer> customerList=new ArrayList<>();
    private OnCostomerClickListner listner;
    // gets customer list
    public void setCustomerList(List<Customer> customerList) {
            // if ExpiredMember activity has called the filter list to show only those customers with expired membership
        if(LiberaryViewModel.SAR_CALL_TAG== MainActivity.EXPIRED_MEMBER_TAG){
            List<Customer> filterList=new ArrayList<>();
            for(Customer customer:customerList){
                if(CustomDate.isLess(CustomDate.stringToDate(customer.getMembershipEndDate()),new CustomDate(System.currentTimeMillis()))){
                    filterList.add(customer);
                }
            }
            this.customerList=filterList;
        }
        else{
            this.customerList = customerList;
        }

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
                holder.phone.setText("Phone : "+customer.getPhone());
        holder.regDate.setText("Reg. Date : "+customer.getRegisteredDate());
        holder.image.setImageBitmap(Converter.byteToImage(customer.getImage()));

        // if membership expired then set it unclickable
        if(CustomDate.isLess(CustomDate.stringToDate(customer.getMembershipEndDate()),new CustomDate(System.currentTimeMillis()))){
            holder.membershipEndDate.setText("Membership expired on : "+customer.getMembershipEndDate());
            if(LiberaryViewModel.SAR_CALL_TAG==NewIssueActivity.ACTIVITY_TAG)
                holder.unclickable();
        }
        else {
            holder.membershipEndDate.setText("Membership till : " + customer.getMembershipEndDate());
        }

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        private TextView name,regDate,membershipEndDate,phone;
        private ImageView image;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CustomerList_CustomerName);
            regDate=itemView.findViewById(R.id.CustomerList_regDate);
            membershipEndDate=itemView.findViewById(R.id.CustomerList_membershipEndDate);
            image=itemView.findViewById(R.id.CustomerList_image);
            phone=itemView.findViewById(R.id.CustomerList_phone);

            itemView.setOnClickListener(view ->{
                listner.onClick(customerList.get(getAdapterPosition()));
            });

        }
        // to make a customer unclickable (if membership expired)
        public void unclickable(){
            itemView.setClickable(false);
            itemView.setAlpha(0.5f);
        }

    }

    public interface OnCostomerClickListner{
        void onClick(Customer customer);
    }
    public void setOnCustomerClickListner(OnCostomerClickListner listner){
            this.listner=listner;
    }
}
