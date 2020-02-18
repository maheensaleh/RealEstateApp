package com.example.mapproject.model;


/**
 * Created by lalit on 9/12/2016.
 */
public class User {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String cnic;
    private String phone;
    private byte[] profile_pic;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getprofile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(byte[] profile_pic) {
        this.profile_pic = profile_pic;
    }



    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getCnic() {
        return cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
