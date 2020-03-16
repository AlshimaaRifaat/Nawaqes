package com.ibsvalleyn.outlet.models.SignUp;

public class User {
    String CustomerId;
    String FirstName;
    String LastName;
    String Email;

    String Phone;
    String Password;
    String GenderId;
    String CountryId;
    String CityId;



    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGenderId() {
        return GenderId;
    }

    public void setGenderId(String genderId) {
        GenderId = genderId;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }
}

