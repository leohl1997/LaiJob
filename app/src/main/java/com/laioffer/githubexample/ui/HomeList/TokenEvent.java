package com.laioffer.githubexample.ui.HomeList;

import com.google.gson.annotations.SerializedName;

public class TokenEvent {
    @SerializedName("user_id")
    public String userId;
    @SerializedName("token")
    public String token;
}
