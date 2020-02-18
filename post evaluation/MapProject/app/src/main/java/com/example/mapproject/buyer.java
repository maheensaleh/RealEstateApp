package com.example.mapproject;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.example.mapproject.adapter.placeautosuggestadapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class buyer extends FragmentActivity implements OnMapReadyCallback {




    private GoogleMap map;
    SearchView searchView;
    SupportMapFragment mapFragment;
    Button filter;
    ArrayList<String> locatio = new ArrayList<String>();
    ArrayList<Integer> myind = new ArrayList<Integer>();
    String info;
    ArrayList<String> result = new ArrayList<String>();
    ArrayList<String> adresses = new ArrayList<String>();
    Integer index_of_clicked_marker;
    flat_for_sell_DB flat_db;
    Integer entries = 0;
    ArrayList<String> address;
    ArrayList<String> rent_sale;
    ArrayList<String> price;
    ArrayList<String> elevator;
    ArrayList<String> balcony;
    ArrayList<String> floors;
    ArrayList<String> rooms;
    ArrayList<byte[]> img1;
    ArrayList<byte[]> img2;
    ArrayList<byte[]> img3;
    ArrayList<byte[]> img4;
    ArrayList<String> area_size;
    ArrayList<String> rating_comment;
    ArrayList<String> rating_value;
    ArrayList<String> prop_type;
    ArrayList<String> owner;
    List<HashMap<String, String>> completedata;
    ArrayList<Integer> indexes = new ArrayList<Integer>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);


        //getting the filters from popup activity
        try {
            Intent intent = getIntent();
            result = intent.getStringArrayListExtra("filters");
            System.out.println("got it filters= " + result);
        } catch (Exception e) {

        }
        flat_db = new flat_for_sell_DB(this);



//intializing thing to retrive from data base and save in them
        address = new ArrayList<String>();
        rent_sale = new ArrayList<String>();
        price = new ArrayList<String>();
        elevator = new ArrayList<String>();
        balcony = new ArrayList<String>();
        floors = new ArrayList<String>();
        rooms = new ArrayList<String>();
        img1 = new ArrayList<byte[]>();
        img2 = new ArrayList<byte[]>();
        img3 = new ArrayList<byte[]>();
        img4 = new ArrayList<byte[]>();

        area_size = new ArrayList<String>();
        rating_comment = new ArrayList<String>();
        rating_value = new ArrayList<String>();
        prop_type = new ArrayList<String>();
        owner = new ArrayList<String>();

        Cursor res = flat_db.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }

        // read values form database
        while (res.moveToNext()) {
            entries += 1;
            address.add(res.getString(1));
            rent_sale.add(res.getString(2));
            price.add(res.getString(3));
            elevator.add(res.getString(4));
            balcony.add(res.getString(5));
            floors.add(res.getString(6));
            rooms.add(res.getString(7));
            img1.add(res.getBlob(8));
            img2.add(res.getBlob(16));
            img3.add(res.getBlob(17));
            img4.add(res.getBlob(18));
            area_size.add(res.getString(10));
            rating_comment.add(res.getString(11));
            rating_value.add(res.getString(12));
            prop_type.add(res.getString(13));
            owner.add(res.getString(14));
        }

//getting data from data base
        completedata = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < entries; i++) {

            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("address", address.get(i));
            temp.put("rent_sale", rent_sale.get(i));
            temp.put("price", price.get(i));
            temp.put("elevator", elevator.get(i));
            temp.put("balcony", balcony.get(i));
            temp.put("floors", floors.get(i));
            temp.put("rooms", rooms.get(i));
            temp.put("area_size", area_size.get(i));
            temp.put("rating_comment", rating_comment.get(i));
            temp.put("rating_value", rating_value.get(i));
            temp.put("prop_type", prop_type.get(i));
            temp.put("owner", owner.get(i));
            completedata.add(temp);

        }

//for the filteration work... comparing indexes
        ArrayList<Integer> ind_address = new ArrayList<Integer>();
        ArrayList<Integer> ind_rent_sale = new ArrayList<Integer>();
        ArrayList<Integer> ind_price = new ArrayList<Integer>();
        ArrayList<Integer> ind_rooms = new ArrayList<Integer>();
        ArrayList<Integer> ind_prop_type = new ArrayList<Integer>();

        if (result != null) {


            for (int x = 0; x < rent_sale.size(); x++) {
                if ((rent_sale.get(x)).equals(result.get(1))) {
                    ind_rent_sale.add(x);
                }
            }


            for (int x = 0; x < prop_type.size(); x++) {
                if ((prop_type.get(x)).equals(result.get(0))) {
                    ind_prop_type.add(x);
                }
            }


            for (int x = 0; x < rooms.size(); x++) {
                if ((rooms.get(x)).equals(result.get(2))) {
                    ind_rooms.add(x);
                }
            }

            for (int x = 0; x < price.size(); x++) {
                if (Integer.parseInt(price.get(x)) >= Integer.parseInt(result.get(3)) & Integer.parseInt(price.get(x)) <= Integer.parseInt(result.get(4))) {
                    ind_price.add(x);
                }
            }


            for (int x = 0; x < ind_price.size(); x++) {
                Integer check = 0;

                for (int y = 0; y < ind_prop_type.size(); y++) {
                    if (ind_prop_type.get(y) == ind_price.get(x)) {
                        check++;
                        break;
                    }
                }

                for (int y = 0; y < ind_rooms.size(); y++) {
                    if (ind_rooms.get(y) == ind_price.get(x)) {
                        check++;
                        break;
                    }
                }

                for (int y = 0; y < ind_rent_sale.size(); y++) {
                    if (ind_rent_sale.get(y) == ind_price.get(x)) {
                        check++;
                        break;
                    }
                }

                if (check == 3) {
                    myind.add(ind_price.get(x));
                    String coord = getLocationFromAddress(address.get(ind_price.get(x)));
                    locatio.add(coord);
                }

            }



        }

//auto complete location search view
        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.location_buyer);
        autoCompleteTextView.setAdapter(new placeautosuggestadapter(buyer.this, android.R.layout.simple_list_item_1));


        Button search = findViewById(R.id.search_buyer);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String searching = autoCompleteTextView.getText().toString();
                    String latlng = getLocationFromAddress(searching).toString();
                    String searched = latlng;
                    String[] vals = searched.split(",");
                    LatLng loca = new LatLng(Double.parseDouble(vals[0]), Double.parseDouble(vals[1]));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(loca, 12));


                } catch (Exception e) {

                }
            }
        });



// openning the popup filter page

        filter = findViewById(R.id.filter);
        final Intent popup = new Intent(buyer.this, popupactivity.class);
        filter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(popup);

            }

        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }





    //gets cityname from location's latitude and longitude
    private String getCityname(LatLng latLng) {

        String mycityname = " ";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String add = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            add = addresses.get(0).getAddressLine(0);
            mycityname = addresses.get(0).getLocality();
            adresses.add(info);
            System.out.println(adresses);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return add;
    }




//to convert address in to latitude and longitude
    public String getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            return lat + "," + lng;

        } catch (Exception e) {
            return null;
        }
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng saddar = new LatLng(24.8539874   ,67.0124112);
        map.moveCamera(CameraUpdateFactory.newLatLng(saddar));



//placing markers on the map a/c to database
        if (result != null) {

            for (int x = 0; x < locatio.size(); x++) {
                String CSV = locatio.get(x);
                String[] values = CSV.split(",");
                LatLng location = new LatLng(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
                map.addMarker(new MarkerOptions().position(location).title(address.get(myind.get(x))));
            }

        } else {
            for (int i = 0; i < address.size(); i++) {
                String co = getLocationFromAddress(address.get(i));
                String CSV = co;
                String[] values = CSV.split(",");
                LatLng location = new LatLng(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
                map.addMarker(new MarkerOptions().position(location).title(address.get(i)));

            }
        }




        // if any marker is clicked..... showing details on another page
        final Intent details = new Intent(buyer.this, propertydetails.class);
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String titleofmarker = marker.getTitle();
                details.putExtra("details", titleofmarker);
                Intent gotoprop = new Intent(buyer.this, propertydetails.class);


                for (int i = 0; i < address.size(); i++) {
                    if (address.get(i).equals(titleofmarker)) {
                        index_of_clicked_marker = i;
                        break;
                    }
                }


                gotoprop.putExtra("alldata", completedata.get(index_of_clicked_marker));
                gotoprop.putExtra("img1", img1.get(index_of_clicked_marker));
                gotoprop.putExtra("img2", img2.get(index_of_clicked_marker));
                gotoprop.putExtra("img3", img3.get(index_of_clicked_marker));
                gotoprop.putExtra("img4", img4.get(index_of_clicked_marker));
                gotoprop.putExtra("status","unselected");

                startActivity(gotoprop);
                return false;


            }

        });


    }
}