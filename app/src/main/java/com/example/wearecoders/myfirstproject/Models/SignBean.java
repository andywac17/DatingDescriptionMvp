package com.example.wearecoders.myfirstproject.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by We Are Coders on 2/7/2018.
 */

public class SignBean {
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("date_birth")
    private int date_birth;
    @SerializedName("interested_in")
    private String interested_in;
    @SerializedName("country")
    private String country;
    @SerializedName("pin_code")
    private int pin_code;
    @SerializedName("login_token")
    private String loginToken;

    public SignBean(String first_name, String last_name, int date_birth, String interested_in, String country, int pin_code) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_birth = date_birth;
        this.interested_in = interested_in;
        this.country = country;
        this.pin_code = pin_code;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(int date_birth) {
        this.date_birth = date_birth;
    }

    public String getInterested_in() {
        return interested_in;
    }

    public void setInterested_in(String interested_in) {
        this.interested_in = interested_in;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPin_code() {
        return pin_code;
    }

    public void setPin_code(int pin_code) {
        this.pin_code = pin_code;
    }
}
