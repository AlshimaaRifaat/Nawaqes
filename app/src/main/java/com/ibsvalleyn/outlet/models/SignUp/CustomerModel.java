package com.ibsvalleyn.outlet.models.SignUp;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerModel {

    @SerializedName("Customer_Id")
    @Expose
    private int customerId;
    @SerializedName("CustomerGuid")
    @Expose
    private String customerGuid;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("AdminComment")
    @Expose
    private String adminComment;
    @SerializedName("Customer_Active")
    @Expose
    private Boolean customerActive;
    @SerializedName("Customer_Deleted")
    @Expose
    private Boolean customerDeleted;
    @SerializedName("Customer_Phone")
    @Expose
    private String customerPhone;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("LanguageId")
    @Expose
    private Object languageId;
    @SerializedName("AccessToken_Id")
    @Expose
    private Integer accessTokenId;
    @SerializedName("AccessToken")
    @Expose
    private String accessToken;
    @SerializedName("AccessToken_Created_At")
    @Expose
    private String accessTokenCreatedAt;
    @SerializedName("AccessToken_Expires_At")
    @Expose
    private String accessTokenExpiresAt;
    @SerializedName("fName")
    @Expose
    private String fName;
    @SerializedName("lName")
    @Expose
    private String lName;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerGuid() {
        return customerGuid;
    }

    public void setCustomerGuid(String customerGuid) {
        this.customerGuid = customerGuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Boolean getCustomerActive() {
        return customerActive;
    }

    public void setCustomerActive(Boolean customerActive) {
        this.customerActive = customerActive;
    }

    public Boolean getCustomerDeleted() {
        return customerDeleted;
    }

    public void setCustomerDeleted(Boolean customerDeleted) {
        this.customerDeleted = customerDeleted;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Object languageId) {
        this.languageId = languageId;
    }

    public Integer getAccessTokenId() {
        return accessTokenId;
    }

    public void setAccessTokenId(Integer accessTokenId) {
        this.accessTokenId = accessTokenId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenCreatedAt() {
        return accessTokenCreatedAt;
    }

    public void setAccessTokenCreatedAt(String accessTokenCreatedAt) {
        this.accessTokenCreatedAt = accessTokenCreatedAt;
    }

    public String getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    public void setAccessTokenExpiresAt(String accessTokenExpiresAt) {
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }


}

