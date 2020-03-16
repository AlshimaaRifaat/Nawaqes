package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class CustomerInfo {


    @Expose
    @SerializedName("Gender")
    private String gender;
    @Expose
    @SerializedName("CityName")
    private String cityname;
    @Expose
    @SerializedName("CountryName")
    private String countryname;
    @Expose
    @SerializedName("LastName")
    private String lastname;
    @Expose
    @SerializedName("FirstName")
    private String firstname;
    @Expose
    @SerializedName("AccessToken_Expires_At")
    private String accesstokenExpiresAt;
    @Expose
    @SerializedName("AccessToken_Created_At")
    private String accesstokenCreatedAt;
    @Expose
    @SerializedName("AccessToken")
    private String accesstoken;
    @Expose
    @SerializedName("AccessToken_Id")
    private int accesstokenId;
    @Expose
    @SerializedName("FullName")
    private String fullname;
    @Expose
    @SerializedName("Customer_Phone")
    private String customerPhone;
    @Expose
    @SerializedName("Customer_Deleted")
    private boolean customerDeleted;
    @Expose
    @SerializedName("Customer_Active")
    private boolean customerActive;
    @Expose
    @SerializedName("AdminComment")
    private String admincomment;
    @Expose
    @SerializedName("Email")
    private String email;
    @Expose
    @SerializedName("Customer_Id")
    private int customerId;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAccesstokenExpiresAt() {
        return accesstokenExpiresAt;
    }

    public void setAccesstokenExpiresAt(String accesstokenExpiresAt) {
        this.accesstokenExpiresAt = accesstokenExpiresAt;
    }

    public String getAccesstokenCreatedAt() {
        return accesstokenCreatedAt;
    }

    public void setAccesstokenCreatedAt(String accesstokenCreatedAt) {
        this.accesstokenCreatedAt = accesstokenCreatedAt;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public int getAccesstokenId() {
        return accesstokenId;
    }

    public void setAccesstokenId(int accesstokenId) {
        this.accesstokenId = accesstokenId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public boolean getCustomerDeleted() {
        return customerDeleted;
    }

    public void setCustomerDeleted(boolean customerDeleted) {
        this.customerDeleted = customerDeleted;
    }

    public boolean getCustomerActive() {
        return customerActive;
    }

    public void setCustomerActive(boolean customerActive) {
        this.customerActive = customerActive;
    }

    public String getAdmincomment() {
        return admincomment;
    }

    public void setAdmincomment(String admincomment) {
        this.admincomment = admincomment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
