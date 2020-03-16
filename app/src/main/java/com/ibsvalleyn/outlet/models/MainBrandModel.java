package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class MainBrandModel {


    @Expose
    @SerializedName("PicUrl")
    private String PicUrl;
    @Expose
    @SerializedName("SeoFilename")
    private String SeoFilename;
    @Expose
    @SerializedName("pictureId")
    private int pictureId;
    @Expose
    @SerializedName("brandName")
    private String brandName;
    @Expose
    @SerializedName("BrandId")
    private int BrandId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    public String getSeoFilename() {
        return SeoFilename;
    }

    public void setSeoFilename(String SeoFilename) {
        this.SeoFilename = SeoFilename;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int BrandId) {
        this.BrandId = BrandId;
    }
}
