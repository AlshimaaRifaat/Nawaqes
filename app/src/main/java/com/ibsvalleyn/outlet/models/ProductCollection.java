package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductCollection {

    @Expose
    @SerializedName("id")
    public int id;
    @Expose
    @SerializedName("Name")
    public String Name;
    @Expose
    @SerializedName("Price")
    public double Price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
