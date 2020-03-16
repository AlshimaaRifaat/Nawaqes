package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsOfBrandListModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ShortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("Price")
    @Expose
    private float price;
    @SerializedName("HasDiscountsApplied")
    @Expose
    private boolean hasDiscountsApplied;
    @SerializedName("PictureId")
    @Expose
    private int pictureId;
    @SerializedName("SeoFilename")
    @Expose
    private String seoFilename;
    @SerializedName("AltAttribute")
    @Expose
    private Object altAttribute;
    @SerializedName("OldPrice")
    @Expose
    private float oldPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isHasDiscountsApplied() {
        return hasDiscountsApplied;
    }

    public void setHasDiscountsApplied(boolean hasDiscountsApplied) {
        this.hasDiscountsApplied = hasDiscountsApplied;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getSeoFilename() {
        return seoFilename;
    }

    public void setSeoFilename(String seoFilename) {
        this.seoFilename = seoFilename;
    }

    public Object getAltAttribute() {
        return altAttribute;
    }

    public void setAltAttribute(Object altAttribute) {
        this.altAttribute = altAttribute;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

}