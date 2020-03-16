package com.ibsvalleyn.outlet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class LoginResult {


    @Expose
    @SerializedName("access_token_expires_at")
    private String access_token_expires_at;
    @Expose
    @SerializedName("access_token")
    private String access_token;
    @Expose
    @SerializedName("login_result")
    private boolean login_result;
    @Expose
    @SerializedName("system_message")
    private String system_message;
    @Expose
    @SerializedName("user_message")
    private String user_message;

    public String getAccess_token_expires_at() {
        return access_token_expires_at;
    }

    public void setAccess_token_expires_at(String access_token_expires_at) {
        this.access_token_expires_at = access_token_expires_at;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public boolean getLogin_result() {
        return login_result;
    }

    public void setLogin_result(boolean login_result) {
        this.login_result = login_result;
    }

    public String getSystem_message() {
        return system_message;
    }

    public void setSystem_message(String system_message) {
        this.system_message = system_message;
    }

    public String getUser_message() {
        return user_message;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }
}
