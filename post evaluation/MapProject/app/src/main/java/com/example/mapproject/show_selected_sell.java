package com.example.mapproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import static com.example.mapproject.R.id;
import static com.example.mapproject.R.layout;

public class show_selected_sell extends AppCompatActivity {



    TextView heading,address_view,price_view,prop_area,prop_elevator, prop_balcony,prop_floors,prop_rooms,prop_rating_comment,notifiaction;
    RatingBar prop_rating;
    Intent taker;
    ImageView prop_pic1,main_pic;

    VideoView show_vid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_show_selected_sell);

        taker = getIntent();
        HashMap<String,String> alldata;
        alldata= (HashMap<String, String>) taker.getSerializableExtra("alldata");
        System.out.println("got this "+alldata);

        heading = (TextView) findViewById(id.prop_heading);
        heading.setText(alldata.get("prop_type")+ " For " + alldata.get("rent_sale"));

        address_view = (TextView) findViewById(id.prop_addr);
        address_view.setText(alldata.get("address"));

        prop_area = (TextView) findViewById(id.prop_area);
        prop_area.setText("Property Area : "+ alldata.get("area_size"));

        price_view = (TextView) findViewById(id.prop_price);
        price_view.setText("Price : "+ alldata.get("price")+" PKR ");


        prop_elevator = (TextView) findViewById(id.prop_elevator);
        prop_elevator.setText("Elevator Available : "+ alldata.get("elevator"));

        prop_balcony = (TextView) findViewById(id.prop_balcony);
        prop_balcony.setText("Balcony Available : "+ alldata.get("balcony"));

        prop_floors = (TextView) findViewById(id.prop_floors);
        prop_floors.setText("Total Floors : "+ alldata.get("floors"));

        prop_rooms = (TextView) findViewById(id.prop_rooms);
        prop_rooms.setText("Total Rooms : "+ alldata.get("rooms"));

        prop_rating = (RatingBar) findViewById(id.prop_ratingbar);
        int rate = new Integer(alldata.get("rating_value"));
        prop_rating.setRating(rate);

        prop_rating_comment = (TextView) findViewById(id.prop_rating_comment);
        prop_rating_comment.setText(alldata.get("rating_comment"));

//        prop_pic1 = (ImageView) findViewById(id.prop_pic1);
//        byte[] bite = taker.getByteArrayExtra("img1");
//        Bitmap bmp = BitmapFactory.decodeByteArray(bite, 0, bite.length);
//        prop_pic1.setImageBitmap(Bitmap.createScaledBitmap(bmp, 500, 500, false));

        main_pic = (ImageView) findViewById(id.main_pic);
        byte[] bite2 = taker.getByteArrayExtra("img1");
        Bitmap bmp2 = BitmapFactory.decodeByteArray(bite2, 0, bite2.length);
        main_pic.setImageBitmap(Bitmap.createScaledBitmap(bmp2, 500, 500, false));

//        notifiaction = (TextView) findViewById(id.prop_notify);
//        System.out.println("personnnnnn:"+propertydetails.bywhom);
//        String current_status = notifiaction.getText().toString();
//        String current_status = propertydetails.bywhom;
//        System.out.println("he send it"+current_status);
//        if (current_status.equals("none")){
//            System.out.println("no new notifications");
//        }
//        else{
//            String all = alldata.get("notification");
////            String[] separate = all.split("-",2);
////            notifiaction.setText("You have got a message for this property by "+separate[0]+".Kindly check your SMS inbox");
//            notifiaction.setText("You have got a message for this property by "+current_status+".Kindly check your SMS inbox");
//
//        }





        //set image//
//        byte[] b = taker.getByteArrayExtra("img1");
//        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
//        ImageView image1 = (ImageView) findViewById(R.id.prop_img1);
//        image1.setImageBitmap(bmp);

        //set video
//        Uri vid1_uri = Uri.parse(alldata.get("vid1"));
//        Uri vid1_uri = Uri.parse("content://media/external/video/media/116305");
//        show_vid1 = (VideoView) findViewById(R.id.prop_vid1);
//        System.out.println(vid1_uri);
//        show_vid1.setVideoURI(vid1_uri);

    }

    public void playvid1(View view) {
        show_vid1.start();

    }
    //
    public void see_pics(View view) {

        byte[] bite = taker.getByteArrayExtra("img1");
        byte[] bite2 = taker.getByteArrayExtra("img2");
        byte[] bite3 = taker.getByteArrayExtra("img3");
        byte[] bite4 = taker.getByteArrayExtra("img4");

        Intent gotopics = new Intent(this,pics_list.class);
        gotopics.putExtra("pic1",bite);
        gotopics.putExtra("pic2",bite2);
        gotopics.putExtra("pic3",bite3);
        gotopics.putExtra("pic4",bite4);

        startActivity(gotopics);




    }
}
