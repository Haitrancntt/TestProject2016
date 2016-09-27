package com.example.haitr.testproject2016.Main.Adapter;

/**
 * Created by haitr on 9/27/2016.
 */

public class Bar {
    public int iImage;
    public String sName;
    public String sPrice;
    public String sDetailed;

    public int getImage() {
        return iImage;
    }

    public void setImage(int iImage) {
        this.iImage = iImage;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPrice() {
        return sPrice;
    }

    public void setsPrice(String sPrice) {
        this.sPrice = sPrice;
    }

    public String getsDetailed() {
        return sDetailed;
    }

    public void setsDetailed(String sDetailed) {
        this.sDetailed = sDetailed;
    }

    public Bar(int iImage, String sName, String sPrice, String sDetailed) {
        this.iImage = iImage;
        this.sName = sName;
        this.sPrice = sPrice;
        this.sDetailed = sDetailed;
    }
}
