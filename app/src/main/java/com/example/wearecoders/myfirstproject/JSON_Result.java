package com.example.wearecoders.myfirstproject;

import com.example.wearecoders.myfirstproject.Models.SignBean;
import com.google.gson.annotations.SerializedName;

/**
 * Created by We Are Coders on 2/15/2018.
 */

public class JSON_Result {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private SignBean data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public SignBean getData() {
        return data;
    }
}
