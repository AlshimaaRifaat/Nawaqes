package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeBrandsModel {

    @SerializedName("BrandId")
    @Expose
    private int BrandId;

    @SerializedName("brandName")
    @Expose
    private String brandName;

    @SerializedName("PicUrl")
    @Expose
    private String PicUrl;

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int brandId) {
        BrandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
