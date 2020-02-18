package com.example.mapproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class popupactivity extends Activity {

    Integer check=0;
    Integer counter=1;
    String[] prices= {"price < 50000","50000 < price < 100000","100000 < price < 500000","500000 < price < 1000000","1000000 < price < 2500000","2500000 < price < 5000000","5000000 < price < 10000000","10000000 < price < 50000000", "price > 50000000"};
    Integer[] low_prices={0,50000,100000,500000,1000000,2500000,5000000,10000000,50000000};
    Integer[] high_prices={50000,100000,500000,1000000,2500000,5000000,10000000,50000000,999999999};
    int ind = 0;
    Intent popdown;
    public ArrayList<String> filters= new ArrayList<String>();








    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupactivity);

// to set the size of popup window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;
        getWindow().setLayout((int)(width*0.9),(int)(height*0.6));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=0;
        getWindow().setAttributes(params);




// when close is clicked
        popdown= new Intent(popupactivity.this,buyer.class);
        final Button button =findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(popdown);
                finish();

            }
        });







//no of rooms inc and dec
        final EditText rooms=(EditText)findViewById(R.id.rooms);
        Button inc= (Button) findViewById(R.id.inc);
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                String count=toString().valueOf(counter);
                rooms.setText("rooms = "+count);
            }
        });

        Button dec= (Button) findViewById(R.id.dec);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter!=1){
                    counter--;}
                String count=toString().valueOf(counter);
                rooms.setText("rooms = "+count);
            }
        });




        //initialing views
        final CheckBox flat = (CheckBox) findViewById(R.id.flat);
        final CheckBox banglow = (CheckBox) findViewById(R.id.banglow);
        final CheckBox  penthouse= (CheckBox) findViewById(R.id.penthouse);
        final CheckBox rent = (CheckBox) findViewById(R.id.rent);
        final CheckBox buy = (CheckBox) findViewById(R.id.buy);
        final EditText price_range=(EditText)findViewById(R.id.price);
        price_range.setText("price < 50000");
        final Button apply_filter=(Button) findViewById(R.id.apply_filter);
        Button next= (Button) findViewById(R.id.next);
        Button prev= (Button) findViewById(R.id.prev);
        next.setText(">");
        prev.setText("<");
        final TextView error1=(TextView) findViewById(R.id.error1);
        final TextView error2=(TextView) findViewById(R.id.error2);
        final TextView error3=(TextView) findViewById(R.id.error3);
        final TextView error4=(TextView) findViewById(R.id.error4);





        //filters when apply filter is clicked
        apply_filter.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (buy.isChecked() & rent.isChecked()) {
                    Toast.makeText(getApplicationContext(),"buy and rent cant be selected together", Toast.LENGTH_LONG).show();
                }


                if (!flat.isChecked() & banglow.isChecked() & penthouse.isChecked()) {
                    Toast.makeText(getApplicationContext(),"you can either select banglow or penthouse or flat only, but not more than one at a time", Toast.LENGTH_LONG).show();
                }
                if (flat.isChecked() & !banglow.isChecked() & penthouse.isChecked()) {
                    Toast.makeText(getApplicationContext(),"you can either select banglow or penthouse or flat only, but not more than one at a time", Toast.LENGTH_LONG).show();
                }
                if (flat.isChecked() & banglow.isChecked() & !penthouse.isChecked()) {
                    Toast.makeText(getApplicationContext(),"you can either select banglow or penthouse or flat only, but not more than one at a time", Toast.LENGTH_LONG).show();
                }
                if (flat.isChecked() & banglow.isChecked() & penthouse.isChecked()) {
                    Toast.makeText(getApplicationContext(),"you can either select banglow or penthouse or flat only, but not more than one at a time", Toast.LENGTH_LONG).show();
                }




                if ((rent.isChecked() & !buy.isChecked()) | (!rent.isChecked() & buy.isChecked())) {
                    if ((!banglow.isChecked() & flat.isChecked() & !penthouse.isChecked()) | (!banglow.isChecked() & !flat.isChecked() & penthouse.isChecked()) | (banglow.isChecked() & !flat.isChecked() & !penthouse.isChecked())) {
                        if (counter != 0) {

                            if (!banglow.isChecked() & flat.isChecked() & !penthouse.isChecked()) {
                                filters.add("Flat");
                            }
                            if (banglow.isChecked() & !flat.isChecked() & !penthouse.isChecked()) {
                                filters.add("Banglow");
                            }
                            if (!banglow.isChecked() & !flat.isChecked() & penthouse.isChecked()) {
                                filters.add("Penthouse");
                            }

                            if (rent.isChecked() & !buy.isChecked()) {
                                filters.add("Rent");
                            }
                            if (!rent.isChecked() & buy.isChecked()) {
                                filters.add("Sale");
                            }

                            filters.add(counter.toString());




                            String p = price_range.getText().toString();
                            System.out.println("price selected= "+p);

                            for (int x = 0; x < prices.length; x++) {
                                if (p.equals(prices[x])) {
                                    ind = x;
                                    break;
                                }
                            }
                            System.out.println("index to price selected= "+ind);
                            filters.add(low_prices[ind].toString());
                            filters.add(high_prices[ind].toString());





                            System.out.println("filters=  " + filters);

                            popdown.putStringArrayListExtra("filters", filters);

                            startActivity(popdown);
                            finish();


                        } else {
                            error4.setText("kindly fill all the filters correctly");
                        }

                    } else {
                        error4.setText("kindly fill all the filters correctly");                    }


                } else {
                    error4.setText("kindly fill all the filters correctly");
                }

            }
        });








// for price filter
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check<prices.length) {
                    check++;

                }
                if (check==prices.length){
                    check=0;
                }
                price_range.setText(prices[check]);

            }
        });



        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check>0) {
                    check--;

                }
                if (check==0){
                    check=prices.length-1;
                }
                price_range.setText(prices[check]);

            }

        });




    }





    //to remove filters
    public void remove_filters(View view) {

        filters=new ArrayList<String>();
        startActivity(popdown);
        finish();


    }
}


