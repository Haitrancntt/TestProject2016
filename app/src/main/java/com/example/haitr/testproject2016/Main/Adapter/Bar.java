package com.example.haitr.testproject2016.Main.Adapter;

/**
 * Created by haitr on 9/27/2016.
 */

public class Bar {
    private String address, description, district, name, phone, picture, price, time, id;
    private double lat, lng;
    private boolean fav;
    private String[] sArray;

    public String[] getsArray() {
        return sArray;
    }

    public void setsArray(String[] sArray) {
        this.sArray = sArray;
    }

    public Bar() {

    }

    public Bar(String id, String address, String description, String district, String name,
               String phone, String picture, String price, String time,
               double lat, double lng, boolean fav, String[] sArray) {
        this.address = address;
        this.description = description;
        this.district = district;
        this.name = name;
        this.phone = phone;
        this.picture = picture;
        this.sArray = sArray;
        this.price = price;
        this.time = time;
        this.lat = lat;
        this.lng = lng;
        this.fav = fav;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
