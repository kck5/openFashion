package com.bmlmunjal.openfashion;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class User {
    private String name;
    private String mobile;
    private String mail;
    private String pincode;
    private String state;
    private String city;
    private String town;
    private String Address;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public void setCity(String city) {
        City = city;
    }

    private String Locality;
    private String City;

    public User() {
    }

    public User(String name, String mobile,String mail ) {
        this.name = name;
        this.mobile = mobile;
        this.mail = mail;
    }

    public User(String name, String mobile, String pincode, String state, String address, String locality, String city) {
        this.name = name;
        this.mobile = mobile;
        this.pincode = pincode;
        this.state = state;
        this.Address = address;
        this.Locality = locality;
        this.City = city;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPincode() {
        return pincode;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return Address;
    }

    public String getLocality() {
        return Locality;
    }

    public String getCity() {
        return City;
    }
}
