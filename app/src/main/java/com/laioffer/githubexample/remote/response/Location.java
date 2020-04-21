package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("lat")
    public double latitude;
    @SerializedName("lon")
    public double longitude;
}
