package com.example.mapproject.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mapproject.R;
import com.example.mapproject.model.User;
import com.example.mapproject.utils.PreferenceUtils;

public class UserProfile extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    private void initObjects() {
        user = new User();
    }
    String email = PreferenceUtils.getEmail(this);
    String password = PreferenceUtils.getPassword(this);

}
