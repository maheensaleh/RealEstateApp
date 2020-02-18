package com.example.mapproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class flat_for_buy_DB extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="flats_for_buy.db";
    public static final String TABLE_NAME="flats_for_buy_table";
    public static final int DATABASE_VERSION=1;


    public static final String COL_1="ID";
    public static final String COL_2="complete_address";
    public static final String COL_3="rent_sale";
    public static final String COL_4="price";
    public static final String COL_5="elevator";
    public static final String COL_6="balcony";
    public static final String COL_7="floors";
    public static final String COL_8="rooms";
    public static final String COL_9="img1";
    public static final String COL_10="vid1";
    public static final String COL_11="area_size";
    public static final String COL_12="rating_comment";
    public static final String COL_13="rating_value";
    public static final String COL_14="prop_type";
    public static final String COL_15="owner";
    public static final String COL_16="img2";
    public static final String COL_17="img3";
    public static final String COL_18="img4";





    public flat_for_buy_DB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, COMPLETE_ADDRESS TEXT, RENT_SALE TEXT,PRICE TEXT , ELEVATOR TEXT, BALCONY TEXT, FLOORS TEXT, ROOMS TEXT, IMG1 BLOB, VID1 TEXT, AREA_SIZE TEXT, RATING_COMMENT TEXT, RATING_VALUE TEXT,PROP_TYPE TEXT, OWNER TEXT,ADDRESS_LINE TEXT,IMG2 BLOB,IMG3 BLOB,IMG4 BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }




    public boolean insertdata( String complete_address, String rent_sale,  String elevator, String balcony,String floors, String rooms, byte[] img1, byte[] img2,byte[] img3, byte[] img4,String area_size, String rating_comment, String rating_value,String price,String prop_type,String owner){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_2, complete_address);
        contentValues.put(COL_3, rent_sale);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5, elevator);
        contentValues.put(COL_6, balcony);
        contentValues.put(COL_7, floors);
        contentValues.put(COL_8, rooms);
        contentValues.put(COL_9, img1);
        contentValues.put(COL_11,area_size);
        contentValues.put(COL_12,rating_comment);
        contentValues.put(COL_13,rating_value);
        contentValues.put(COL_14,prop_type);
        contentValues.put(COL_15,owner);
        contentValues.put(COL_16,img2);
        contentValues.put(COL_17,img3);
        contentValues.put(COL_18,img4);

        long successful= db.insert(TABLE_NAME,null,contentValues);


        if (successful==-1){
            System.out.println("insertion failed");
            return false;
        }
        else{
            System.out.println("insertion successful!!");
            return true;
        }


    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

}

