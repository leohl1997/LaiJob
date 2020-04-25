package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lon")
    public double longitude;
    @SerializedName("lat")
    public double latitude;

}
