package com.example.liberaryadmin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.ByteArrayOutputStream;

public  class Converter {
    public static byte[] imageToByte(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        byte[] image = stream.toByteArray();
        return image;
    }
    public static Bitmap byteToImage(byte[] imgByte){
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
      return bitmap;
    }
}
