package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class GetWhilstModel {


    @Expose
    @SerializedName("PicUrl")
    private String PicUrl;
    @Expose
    @SerializedName("ProductName")
    private String ProductName;
    @Expose
    @SerializedName("SeoFilename")
    private String SeoFilename;
    @Expose
    @SerializedName("PictureId")
    private int PictureId;
    @Expose
    @SerializedName("Price")
    private int Price;
    @Expose
    @SerializedName("Productid")
    private int Productid;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getSeoFilename() {
        return SeoFilename;
    }

    public void setSeoFilename(String SeoFilename) {
        this.SeoFilename = SeoFilename;
    }

    public int getPictureId() {
        return PictureId;
    }

    public void setPictureId(int PictureId) {
        this.PictureId = PictureId;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getProductid() {
        return Productid;
    }

    public void setProductid(int Productid) {
        this.Productid = Productid;
    }
}
