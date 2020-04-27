package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("user_id")
    public String user_id;
    @SerializedName("firstName")
    public static String FirstName;
    @SerializedName("lastName")
    public static String LastName;
    @SerializedName("phone")
    public static String PhoneNumber;
    @SerializedName("email")
    public static String Email;
    @SerializedName("address")
    public static String Address;
    @SerializedName("dateOfBirth")
    public static String DateOfBirth;
}
