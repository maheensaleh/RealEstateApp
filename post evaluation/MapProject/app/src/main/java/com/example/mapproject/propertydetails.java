package com.example.mapproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class propertydetails extends AppCompatActivity {
    public static String bywhom="none";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    TextView heading,address_view,price_view,prop_area,prop_elevator, prop_balcony,prop_floors,prop_rooms,prop_rating_comment,owner_name;
    RatingBar prop_rating;
    Button send_mess;
    flat_for_sell_DB db;
    String phoneNo,phoneNo2,message;
    Intent gotdetails;
    flat_for_buy_DB db_buy;

    ImageView main_pic;
    HashMap<String,String> alldata;

    byte[] bite;
    byte[] bite2;
    byte[] bite3;
    byte[] bite4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertydetails);
        db = new flat_for_sell_DB(this);
        db_buy = new flat_for_buy_DB(this);

        gotdetails = getIntent();
        alldata = (HashMap<String, String>) gotdetails.getSerializableExtra("alldata");
        System.out.println("got this " + alldata);


        heading = (TextView) findViewById(R.id.Bprop_heading);
        heading.setText(alldata.get("prop_type") + " For " + alldata.get("rent_sale"));

        address_view = (TextView) findViewById(R.id.Bprop_addr);
        address_view.setText(alldata.get("address"));

        prop_area = (TextView) findViewById(R.id.Bprop_area);
        prop_area.setText("Property Area : " + alldata.get("area_size"));

        price_view = (TextView) findViewById(R.id.Bprop_price);
        price_view.setText("Price : " + alldata.get("price") + " PKR ");


        prop_elevator = (TextView) findViewById(R.id.Bprop_elevator);
        prop_elevator.setText("Elevator Available : " + alldata.get("elevator"));

        prop_balcony = (TextView) findViewById(R.id.Bprop_balcony);
        prop_balcony.setText("Balcony Available : " + alldata.get("balcony"));

        prop_floors = (TextView) findViewById(R.id.Bprop_floors);
        prop_floors.setText("Total Floors : " + alldata.get("floors"));

        prop_rooms = (TextView) findViewById(R.id.Bprop_rooms);
        prop_rooms.setText("Total Rooms : " + alldata.get("rooms"));

        prop_rating = (RatingBar) findViewById(R.id.Bprop_ratingbar);
        final int rate = new Integer(alldata.get("rating_value"));
        prop_rating.setRating(rate);

        prop_rating_comment = (TextView) findViewById(R.id.Bprop_rating_comment);
        prop_rating_comment.setText(alldata.get("rating_comment"));


        bite = gotdetails.getByteArrayExtra("img1");
        bite2 = gotdetails.getByteArrayExtra("img2");
        bite3 = gotdetails.getByteArrayExtra("img3");
        bite4 = gotdetails.getByteArrayExtra("img4");

        main_pic = (ImageView) findViewById(R.id.Bmain_pic);
        Bitmap bmp2 = BitmapFactory.decodeByteArray(bite, 0, bite.length);
        main_pic.setImageBitmap(Bitmap.createScaledBitmap(bmp2, 500, 500, false));


        send_mess = (Button) findViewById(R.id.select_prop);
//        String activity_status= gotdetails.getStringExtra("status");
//        if (activity_status.equals("selected")){
//            send_mess.setText("Propery Already Selected !");
//            send_mess.setEnabled(false);
//        }
//        else{
//            System.out.println("starttttttttttt");
//            send_mess.setText("Select This Property!");
//            send_mess.setEnabled(true);
//
//        }
        send_mess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(propertydetails.this, "property selected", Toast.LENGTH_SHORT).show();
        db_buy.insertdata(alldata.get("address"),alldata.get("rent_sale"),
        alldata.get("elevator"),alldata.get("balcony"), alldata.get("floors"),alldata.get("rooms"),bite,bite2,bite3,bite4,
        alldata.get("area_size"),alldata.get("rating_comment"),alldata.get("rating_value"),alldata.get("price"),alldata.get("prop_type"),alldata.get("owner"));
//        sendSMSMessage();
            }
        });

    }

//    private void sendSMSMessage() {
//
//
//        phoneNo = "03332061238";
//        message = "Greetings! This is to inform you that " +
//                "I am interested in one of your " + alldata.get("prop_type")+" ; "+alldata.get("address")+" registered " +
//                "at SAM ESTATES. Can we talk about it? ";
//        bywhom = alldata.get("owner");
//
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.SEND_SMS)) {
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.SEND_SMS},
//                        MY_PERMISSIONS_REQUEST_SEND_SMS);
//            }
//        }
//
//    }

//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    SmsManager smsManager = SmsManager.getDefault();
//
//                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
//                    Toast.makeText(getApplicationContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                    bywhom = alldata.get("owner");
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//        }
//    }

    public void see_pics2(View view) {

        byte[] bite = gotdetails.getByteArrayExtra("img1");
        byte[] bite2 = gotdetails.getByteArrayExtra("img2");
        byte[] bite3 = gotdetails.getByteArrayExtra("img3");
        byte[] bite4 = gotdetails.getByteArrayExtra("img4");

        Intent gotopics = new Intent(this,pics_list.class);
        gotopics.putExtra("pic1",bite);
        gotopics.putExtra("pic2",bite2);
        gotopics.putExtra("pic3",bite3);
        gotopics.putExtra("pic4",bite4);


        startActivity(gotopics);    }
}
