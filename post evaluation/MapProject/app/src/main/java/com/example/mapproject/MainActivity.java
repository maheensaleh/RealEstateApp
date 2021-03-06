package com.example.mapproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mapproject.activities.LoginActivity;
import com.example.mapproject.model.User;
import com.example.mapproject.sql.DatabaseHelper;
import com.example.mapproject.utils.PreferenceUtils;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

//package com.example.home;

public class MainActivity extends AppCompatActivity
        implements tab1.OnFragmentInteractionListener, tab2.OnFragmentInteractionListener {
    private List<User> listUsers;
//
//    public MainActivity(List<User> listUsers) {
//        this.listUsers = listUsers;
//    }


    private TabLayout tablayout;
    private ViewPager viewpager;
    private TabItem tabitem1, tabitem2;
    public PageAdapter pageadapter;
    private Toolbar mytoolbar;
    TextView username;
    private DatabaseHelper databaseHelper;


    // for option menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

               // startActivity(new Intent(MainActivity.this, UsersListActivity.class));
// do something
        case R.id.logout:

            System.out.println("entered onclick----------------------------------------");

            PreferenceUtils.loggedout(MainActivity.this);
            System.out.println("entered onclick 1 ----------------------------------------");

            startActivity(new Intent(MainActivity.this, LoginActivity.class));

            finish();
            return true;



        default:
            return super.onContextItemSelected(item);
    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytoolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);   // to set menus options on toolbar
    //listUsers = new UsersListActivity().listUsers;

        username = (TextView) findViewById(R.id.user_name);
        //username.setText(databaseHelper.userNames);

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tabitem1 = (TabItem) findViewById(R.id.buy);
        tabitem2 = (TabItem) findViewById(R.id.sell);
        viewpager = (ViewPager)findViewById(R.id.myviewpager);

        pageadapter = new PageAdapter(getSupportFragmentManager(),tablayout.getTabCount());
        viewpager.setAdapter(pageadapter);

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewpager.setCurrentItem(tab.getPosition());

                if (tab.getPosition()==0){
                    pageadapter.notifyDataSetChanged();
                }
                else  if (tab.getPosition()==1){
                    pageadapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void add_sell_prop(View view) {
        Intent gotosellprop = new Intent(this, select_prop.class);
        startActivity(gotosellprop);
    }



    public void searchmap(View view) {
        final Intent buy = new Intent(MainActivity.this, buyer.class);
        startActivity(buy);


///
    }
}







