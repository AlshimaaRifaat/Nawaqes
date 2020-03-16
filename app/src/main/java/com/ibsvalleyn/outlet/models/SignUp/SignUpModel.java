package com.ibsvalleyn.outlet.models.SignUp;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpModel {
    @SerializedName("RegisterResult")
    @Expose
    private RegisterResultModel registerResult;
    @SerializedName("Customer")
    @Expose
    private CustomerModel customer;

    public RegisterResultModel getRegisterResult() {
        return registerResult;
    }

    public void setRegisterResult(RegisterResultModel registerResult) {
        this.registerResult = registerResult;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}

