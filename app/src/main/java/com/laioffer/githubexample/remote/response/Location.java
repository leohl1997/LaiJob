package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    @SerializedName("lon")
    public double longitude;
    @SerializedName("lat")
    public double latitude;

}
