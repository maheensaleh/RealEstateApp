package com.example.mapproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;


public class flat extends AppCompatActivity {

    Integer floor_count = 0, room_count = 0, rating_value=0;
    TextView floors_qty, rooms_qty, mRatingScale;
    ImageView img1,img2,img3,img4;
    Spinner spin;
    EditText address, price;
    Bitmap bitmap_1,bitmap_2,bitmap_3,bitmap_4;
    Uri video_uri;
    CheckBox sale, rent, balcony, elevator;
    flat_for_sell_DB flat_db;
    RatingBar mRatingBar;
    String rating_comment = "" ,address_line;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat);

        try {

            Intent got=getIntent();
            String detail =got.getStringExtra("detail");


            TextView property=findViewById(R.id.address_submit);
            property.setText(detail);

        }
        catch (Exception e){

        }

        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);


        floors_qty = (TextView) findViewById(R.id.floors_qty);
        rooms_qty = (TextView) findViewById(R.id.rooms_qty);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        video_uri = null;
        bitmap_1 =null;
        flat_db=new flat_for_sell_DB(this);

        //to set initial scroll to top
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_flat_form);
        scrollView.smoothScrollTo(0, 0);

        // area sizes
        Integer[] users = {800, 1300, 1600, 2200};
        spin = (Spinner) findViewById(R.id.flat_area);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        //rating bar star select
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });

        //rating bar feedback
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        rating_comment ="Not so good";
                        rating_value = 1;
                        break;
                    case 2:
                        rating_comment = "Need some improvement";
                        rating_value = 2;
                        break;
                    case 3:
                        rating_comment = "Good";
                        rating_value = 3;
                        break;
                    case 4:
                        rating_comment = "Great";
                        rating_value = 4;
                        break;
                    case 5:
                        rating_comment = "Perfect";
                        rating_value = 5;
                        break;
                    default:
                        mRatingScale.setText("");
                }
                mRatingScale.setText(rating_comment);

            }
        });


    }





    public void addimage1(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);

    }

    public void addimage2(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }
    public void addimage3(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 2);

    }
    public void addimage4(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 3);

    }



    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            bitmap_1 = (Bitmap) data.getExtras().get("data");
            img1.setImageBitmap(bitmap_1);
        }

        else if (requestCode == 1) {
            bitmap_2 = (Bitmap) data.getExtras().get("data");
            img2.setImageBitmap(bitmap_2);}

        else if (requestCode == 2) {
            bitmap_3 = (Bitmap) data.getExtras().get("data");
            img3.setImageBitmap(bitmap_3);}

        else if (requestCode == 3) {
            bitmap_4 = (Bitmap) data.getExtras().get("data");
            img4.setImageBitmap(bitmap_4);}


    }


    public void addlocation(View view) {
        Intent sell = new Intent(flat.this, seller.class);
        sell.putExtra("type","flat");

        startActivity(sell);
    }

    public void takevid1(View view) {

        Intent video_intent = new Intent();
        video_intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(video_intent, 4);
    }


    public void add_floor(View view) {
        floors_qty = (TextView) findViewById(R.id.floors_qty);
        floor_count += 1;
        floors_qty.setText(floor_count.toString());
    }

    public void add_room(View view) {

        rooms_qty = (TextView) findViewById(R.id.rooms_qty);
        room_count += 1;
        rooms_qty.setText(room_count.toString());
    }

    public void remove_room(View view) {

        rooms_qty = (TextView) findViewById(R.id.rooms_qty);
        if (room_count != 0) {
            room_count -= 1;
        }
        rooms_qty.setText(room_count.toString());
    }

    public void remove_floor(View view) {

        floors_qty = (TextView) findViewById(R.id.floors_qty);
        if (floor_count != 0) {
            floor_count -= 1;
        }
        floors_qty.setText(floor_count.toString());
    }

    public void preview_ad(View view) {

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        //checkboxes values
        sale = (CheckBox) findViewById(R.id.for_sale);
        rent = (CheckBox) findViewById(R.id.for_rent);
        balcony = (CheckBox) findViewById(R.id.balcony);
        elevator = (CheckBox) findViewById(R.id.elevator);


        //create intent

        //create dictionary for details
        HashMap flat_details = new HashMap();

        //views
        address = (EditText) findViewById(R.id.address_submit);
        floors_qty = (TextView) findViewById(R.id.floors_qty);
        rooms_qty = (TextView) findViewById(R.id.rooms_qty);
        price = (EditText) findViewById(R.id.price);

        //setting chk box status
        String rent_sale = "None";
        String balcony_status = "No";
        String elevator_status = "No";

        if (sale.isChecked()) {
            rent_sale = "Sale";
        }
        if (rent.isChecked()) {
            rent_sale = "Rent";
        }
        if (balcony.isChecked()) {
            balcony_status = "Yes";
        }
        if (elevator.isChecked()) {
            elevator_status = "Yes";
        }




        /////////////// COMPLETE FORM FILL CHECKS ///////////////

        //check address given
        if (address.getText().toString().equals("")){
            Toast.makeText(this, "Please enter Address !", Toast.LENGTH_SHORT).show();
            return;
        }

        // both sell rent unchk
        if (sale.isChecked() == false && rent.isChecked() == false) {
            Toast.makeText(this, "Select any one : FOR SALE or FOR RENT", Toast.LENGTH_SHORT).show();
            return;
        }

        //both sale rent checked
        if (sale.isChecked() == true && rent.isChecked() == true) {
            Toast.makeText(this, "Cannot select both : FOR SALE or FOR RENT", Toast.LENGTH_SHORT).show();
            return;
        }

        //check price given
        if (price.getText().toString().equals("")){
            Toast.makeText(this, "Please provide property price !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rating_comment.equals("")){
            Toast.makeText(this, "Please provide property rating !", Toast.LENGTH_SHORT).show();
            return;
        }

        // check room not zero
        if (floors_qty.getText().toString().equals("0")){
            Toast.makeText(this, "Please provide number of floors!", Toast.LENGTH_SHORT).show();
            return;
        }

        //chek floor not zero
        if (rooms_qty.getText().toString().equals("0")){
            Toast.makeText(this, "Please enter number of rooms !", Toast.LENGTH_SHORT).show();
            return;
        }

        //check image1
        if (bitmap_1 == null  ){
            Toast.makeText(this, "Picture 1 not provided !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bitmap_2 == null  ){
            Toast.makeText(this, "Picture 2 not provided !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bitmap_3 == null  ){
            Toast.makeText(this, "Picture 3 not provided !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bitmap_4 == null  ){
            Toast.makeText(this, "Picture 4 not provided !", Toast.LENGTH_SHORT).show();
            return;
        }

        //send image//
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        bitmap_1.compress(Bitmap.CompressFormat.PNG, 100, baos1);
        byte[] img1_bytes = baos1.toByteArray();


        ByteArrayOutputStream baos2= new ByteArrayOutputStream();
        bitmap_2.compress(Bitmap.CompressFormat.PNG, 100, baos2);
        byte[] img2_bytes = baos2.toByteArray();


        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        bitmap_3.compress(Bitmap.CompressFormat.PNG, 100, baos3);
        byte[] img3_bytes = baos3.toByteArray();


        ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
        bitmap_4.compress(Bitmap.CompressFormat.PNG, 100, baos4);
        byte[] img4_bytes = baos4.toByteArray();



        address_line = "something";
        flat_db.insertdata(address.getText().toString(),rent_sale,
                elevator_status,balcony_status, floors_qty.getText().toString(),rooms_qty.getText().toString(),img1_bytes,img2_bytes,img3_bytes,img4_bytes,
                spin.getSelectedItem().toString(),rating_comment,rating_value.toString(),price.getText().toString(),"Flat","russel");

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Intent to_flat_preview = new Intent(flat.this, MainActivity.class);

        startActivity(to_flat_preview);

    }
}
