package com.example.mapproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class addressgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressgot);


        Intent got=getIntent();
        String detail =got.getStringExtra("detail");


        TextView property=findViewById(R.id.seller_address);
        property.setText(detail);

    }
}
