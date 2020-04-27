package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInfo implements Serializable {
//    @SerializedName("user_id")
//    public static String userId;
    @SerializedName("profile")
    public Profile profile;
    @SerializedName("education")
    public Education education;
    @SerializedName("work")
    public Work work;
}
