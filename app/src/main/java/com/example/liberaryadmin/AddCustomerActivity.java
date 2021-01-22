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
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liberaryadmin.Helpers.Converter;
import com.example.liberaryadmin.Helpers.CustomDate;
import com.example.liberaryadmin.Model.LiberaryViewModel;
import com.example.liberaryadmin.database.ObjectClasses.Customer;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int IMG_TAG = 1;
    private TextView t_name,t_address,t_phone;
    private Spinner sp_membership;
    private ImageView i_image;
    private ImageView b_back;
    private Button b_button;
    private CardView cv_selectImage;

    private LiberaryViewModel viewModelInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        init();
    }

    private void init() {
        t_name=findViewById(R.id.AddCustomer_name);
        t_address=findViewById(R.id.AddCustomer_address);
        t_phone=findViewById(R.id.AddCustomer_phone);
        i_image=findViewById(R.id.AddCustomer_image);
        b_button=findViewById(R.id.AddCustomer_addButton);
        sp_membership=findViewById(R.id.AddCustomer_membershipDuration);
        cv_selectImage=findViewById(R.id.AddCustomer_addImage);
        b_back=findViewById(R.id.AddCustomer_back);
        viewModelInstance=new ViewModelProvider(this).get(LiberaryViewModel.class);

        b_button.setOnClickListener(this);
        cv_selectImage.setOnClickListener(this);
        b_back.setOnClickListener(this);



        String[] spinnerList={"1 Month","3 Months","6 Months","9 Months","12 Months"};

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_membership.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.AddCustomer_addButton:
                check();
                break;
            case R.id.AddCustomer_addImage:
                addImage();
                break;
            case R.id.AddCustomer_back:
                super.onBackPressed();
                break;
        }
    }

    private void check() {
        if(t_name.getText().toString().trim().isEmpty()){
            t_name.setError("Enter Book name");
        }
        else if(t_address.getText().toString().trim().isEmpty()){
            t_address.setError("Enter address name");
        }
        else if(t_phone.getText().toString().trim().isEmpty()){
            t_address.setError("Enter phone");
        }
        else
            AddCustomer();
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

    private void AddCustomer() {
        String name,address,phone,registerDate,membershipStartDate,membershipEndDate;
        Integer membershipDuration;
        name=t_name.getText().toString();
        address=t_address.getText().toString();
        phone=t_phone.getText().toString();
        membershipDuration=Math.max(sp_membership.getSelectedItemPosition()*3,1);
        CustomDate date=new CustomDate(System.currentTimeMillis());
        registerDate=date.dateToString();
        membershipStartDate=date.dateToString();
        membershipEndDate=date.getExtendedDate(membershipDuration).dateToString();
        BitmapDrawable bitmapDrawable;
             bitmapDrawable = (BitmapDrawable) i_image.getDrawable();
        Bitmap bmp = bitmapDrawable.getBitmap();
        byte[] image = Converter.imageToByte(bmp);
        if(image.length>150000){
            Toast.makeText(this, "Image size too big", Toast.LENGTH_LONG).show();
        }
        else {
            Customer customer = new Customer(name, phone, address, registerDate, membershipStartDate, membershipEndDate, image);
            viewModelInstance.insertCustomer(customer);
            Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }
}