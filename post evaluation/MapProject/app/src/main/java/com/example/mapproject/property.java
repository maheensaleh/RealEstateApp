package com.example.mapproject;

public class property {
    private  byte[] img;
    private  String addr;
    public property(byte[] img,String addr){
        this.img = img;
        this.addr = addr;
    }

    public  byte[] getImg(){
        return  img;

    }

    public String getAddr(){
        return addr;
    }

    public  void  setImg(byte[] img){
        this.img = img;
    }


}


