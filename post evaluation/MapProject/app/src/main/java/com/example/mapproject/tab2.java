package com.example.mapproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class
tab2 extends Fragment {


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

    ArrayList<String> vid1;
    ArrayList<String> area_size;
    ArrayList<String> rating_comment;
    ArrayList<String> rating_value;
    ArrayList<String> prop_type;
    ArrayList<String> owner;
    ArrayList<String> address_line;
    ArrayList<String> notification;
    ArrayList<property> listview_details;
    property entry;


    Integer entries;

    Intent gotoprop;

    private OnFragmentInteractionListener mListener;

    public tab2() {
        // Required empty public constructor
    }

    public static tab2 newInstance(String param1, String param2) {
        tab2 fragment = new tab2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // MAIN WORK.//
    @Override  // this method shows the content of the  fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        address = new ArrayList<String>();
        rent_sale    = new ArrayList<String>();
        price       = new ArrayList<String>();
        elevator     = new ArrayList<String>();
        balcony     = new ArrayList<String>();
        floors      = new ArrayList<String>();
        rooms       = new ArrayList<String>();
        img1         = new ArrayList<byte[]>();
        img2         = new ArrayList<byte[]>();
        img3         = new ArrayList<byte[]>();
        img4         = new ArrayList<byte[]>();

        vid1   = new ArrayList<String>();
        area_size   = new ArrayList<String>();
        rating_comment = new ArrayList<String>();
        rating_value = new ArrayList<String>();
        prop_type = new ArrayList<String>();
        owner = new ArrayList<String>();
        address_line = new ArrayList<String>();
        notification = new ArrayList<String>();
        listview_details         = new ArrayList<property>();

        entries=0;
        flat_for_sell_DB flat_db;

        flat_db=new flat_for_sell_DB(getActivity());

        Cursor res=flat_db.getAllData();
        if (res.getCount()==0){
            System.out.println("");
        }

        // read values form database
        while (res.moveToNext()) {
            entries+=1;
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

            vid1.add(res.getString(9));
            area_size.add(res.getString(10));
            rating_comment.add(res.getString(11));
            rating_value.add(res.getString(12));
            prop_type.add(res.getString(13));
            owner.add(res.getString(14));
            address_line.add(res.getString(15));
            entry= new property(res.getBlob(8),res.getString(1));
            listview_details.add(entry);


        }


        View view = inflater.inflate(R.layout.fragment_tab2, container, false);


        final List<HashMap<String ,String >> alldata = new ArrayList<HashMap<String ,String>>();
        for (int i = 0; i<entries; i++){

            HashMap<String ,String> temp = new HashMap<String ,String>();
            temp.put("address",     address.get(i));
            temp.put("rent_sale",     rent_sale.get(i));
            temp.put("price",         price.get(i));
            temp.put("elevator",      elevator.get(i));
            temp.put("balcony",       balcony.get(i));
            temp.put("floors",        floors.get(i));
            temp.put("rooms",         rooms.get(i));
            temp.put("vid1",     vid1.get(i));
            temp.put("area_size",     area_size.get(i));
            temp.put("rating_comment", rating_comment.get(i));
            temp.put("rating_value",  rating_value.get(i));
            temp.put("prop_type",  prop_type.get(i));
            temp.put("owner",  owner.get(i));
            temp.put("addressline",  owner.get(i));
            temp.put("notification",  owner.get(i));
            alldata.add(temp);

        }

        ListView mylistview = (ListView) view.findViewById(R.id.listViewsell);
        System.out.println("images  for adapter are "+img1);
        prop_listadapter adapter = new prop_listadapter((FragmentActivity) getContext(), R.layout.sell_tab_listview_item_display,listview_details);
        mylistview.setAdapter(adapter);



        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                gotoprop = new Intent(getActivity(),show_selected_sell.class);
                gotoprop.putExtra("alldata",alldata.get(i));
                gotoprop.putExtra("img1",img1.get(i));
                gotoprop.putExtra("img2",img2.get(i));
                gotoprop.putExtra("img3",img3.get(i));
                gotoprop.putExtra("img4",img4.get(i));
                System.out.println("sending " + alldata  );
                startActivity(gotoprop);
            }
        });

        System.out.println("images are in bytes as : " + img1);
        //////////////
        return view;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
