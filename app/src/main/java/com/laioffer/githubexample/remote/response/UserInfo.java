package com.laioffer.githubexample.remote.response;

<<<<<<< HEAD
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
=======
public class UserInfo {
    public String userId;
    public String name;
>>>>>>> bc0c1af1071fcf9a78b627c46fc9c842d2395ee1
}
