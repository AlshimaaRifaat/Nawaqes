package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartModel {
    @SerializedName("Productid")
    @Expose
    private int id;

    @SerializedName("ProductName")
    @Expose
    private String name;

    @SerializedName("Quantity")
    @Expose
    private int Quantity;

    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("PicUrl")
    @Expose
    private String image_url;

    @SerializedName("iswishlist")
    @Expose
    private Integer iswishlist;

    @SerializedName("OldPrice")
    @Expose
    private double sellingPrice;

    @SerializedName("Price")
    @Expose
    private double price;

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIswishlist() {
        return iswishlist;
    }

    public void setIswishlist(Integer iswishlist) {
        this.iswishlist = iswishlist;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    private int isChecked;

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
//
//    public List<SpecificationAttribute> getAttribute() {
//        return attribute;
//    }
//
//    public void setAttribute(List<SpecificationAttribute> attribute) {
//        this.attribute = attribute;
//    }
}