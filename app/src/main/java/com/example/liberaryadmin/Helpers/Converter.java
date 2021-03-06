package com.example.liberaryadmin.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jetbrains.annotations.NotNull;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;

public  class Converter { // can use @converter for DB room library
    public static byte @NotNull [] imageToByte(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] image = stream.toByteArray();
        return image;
    }  // converts bitmap to byte[] format. Used to store image to DB
    public static Bitmap byteToImage(byte[] imgByte){
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
      return bitmap;
    }  // Converts byte[] to Bitmap formet . Used to fetch image from DB


}
