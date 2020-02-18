package com.example.mapproject;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class getdata extends AppCompatActivity {


    flat_for_sell_DB flat_db;


    ArrayList<String> address;
    ArrayList<String> rent_sale;
    ArrayList<String> price;
    ArrayList<String> elevator;
    ArrayList<String> balcony;
    ArrayList<String> floors;
    ArrayList<String> rooms;
    ArrayList<byte[]> img1;
    ArrayList<String> vid1;
    ArrayList<String> area_size;
    ArrayList<String> rating_comment;
    ArrayList<String> rating_value;
    ArrayList<String> prop_type;
    ArrayList<String> owner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdata);


        flat_db=new flat_for_sell_DB(this);


        address = new ArrayList<String>();
        rent_sale    = new ArrayList<String>();
        price       = new ArrayList<String>();
        elevator     = new ArrayList<String>();
        balcony     = new ArrayList<String>();
        floors      = new ArrayList<String>();
        rooms       = new ArrayList<String>();
        img1         = new ArrayList<byte[]>();
        vid1   = new ArrayList<String>();
        area_size   = new ArrayList<String>();
        rating_comment = new ArrayList<String>();
        rating_value = new ArrayList<String>();
        prop_type = new ArrayList<String>();
        owner = new ArrayList<String>();


        Cursor res=flat_db.getAllData();
        if (res.getCount()==0){
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }

        // read values form database
        while (res.moveToNext()) {
            address.add(res.getString(1));
            rent_sale.add(res.getString(2));
            price.add(res.getString(3));
            elevator.add(res.getString(4));
            balcony.add(res.getString(5));
            floors.add(res.getString(5));
            rooms.add(res.getString(7));
            img1.add(res.getBlob(8));
            vid1.add(res.getString(9));
            area_size.add(res.getString(10));
            rating_comment.add(res.getString(11));
            rating_value.add(res.getString(12));
            prop_type.add(res.getString(13));
            owner.add(res.getString(14));

        }

        System.out.println(address);



    }
}
