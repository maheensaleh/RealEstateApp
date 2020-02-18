package com.example.mapproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class select_prop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prop_select);

    }

    public void add_flat_property(View view) {

        Intent go_flatform = new  Intent(select_prop.this,flat.class);
        startActivity(go_flatform);
    }


    public void add_penthouse_property(View view) {

        Intent go_penthouseform = new  Intent(select_prop.this,penthouse.class);
        startActivity(go_penthouseform);
    }

    public void add_banglow_property(View view) {

        Intent go_banglowform = new  Intent(select_prop.this,banglow.class);
        startActivity(go_banglowform);
    }
}
