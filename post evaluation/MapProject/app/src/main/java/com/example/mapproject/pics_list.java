package com.example.mapproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.mapproject.R.layout;

public class pics_list extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.pic_list);

        Intent getter = getIntent();
        byte[] b1 = getter.getByteArrayExtra("pic1");
        Bitmap bmp1 = BitmapFactory.decodeByteArray(b1, 0, b1.length);
        ImageView image1 = (ImageView) findViewById(R.id.showpic1);
        image1.setImageBitmap(Bitmap.createScaledBitmap(bmp1, 500, 500, false));


        byte[] b2 = getter.getByteArrayExtra("pic2");
        Bitmap bmp2 = BitmapFactory.decodeByteArray(b2, 0, b2.length);
        ImageView image2 = (ImageView) findViewById(R.id.showpic2);
        image2.setImageBitmap(Bitmap.createScaledBitmap(bmp2, 500, 500, false));


        byte[] b3 = getter.getByteArrayExtra("pic3");
        Bitmap bmp3 = BitmapFactory.decodeByteArray(b3, 0, b3.length);
        ImageView image3 = (ImageView) findViewById(R.id.showpic3);
        image3.setImageBitmap(Bitmap.createScaledBitmap(bmp3, 500, 500, false));


        byte[] b4 = getter.getByteArrayExtra("pic4");
        Bitmap bmp4 = BitmapFactory.decodeByteArray(b4, 0, b4.length);
        ImageView image4 = (ImageView) findViewById(R.id.showpic4);
        image4.setImageBitmap(Bitmap.createScaledBitmap(bmp4, 500, 500, false));


    }


}
