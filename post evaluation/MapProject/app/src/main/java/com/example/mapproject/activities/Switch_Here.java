package com.example.mapproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mapproject.R;
import com.example.mapproject.utils.PreferenceUtils;

public class Switch_Here extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch__here);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout:
                PreferenceUtils.loggedout(this);
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }
    public void onClick(View view) {
        startActivity(new Intent(this, UsersListActivity.class));
    }
}
