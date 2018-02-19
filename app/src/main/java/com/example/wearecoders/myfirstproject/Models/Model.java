package com.example.wearecoders.myfirstproject.Models;

/**
 * Created by We Are Coders on 2/12/2018.
 */

public class Model {

    public String name, DOB, gender, interest, country, pincode, description;

    public Model(String name, String DOB, String gender, String interest, String country, String pincode, String description) {
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.interest= interest;
        this.country= country;
        this.pincode= pincode;
        this.description= description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
