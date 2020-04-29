package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("user_id")
    public String user_id;
    @SerializedName("firstName")
    public  String FirstName;
    @SerializedName("lastName")
    public  String LastName;
    @SerializedName("phone")
    public  String PhoneNumber;
    @SerializedName("email")
    public  String Email;
    @SerializedName("address")
    public  String Address;
    @SerializedName("dateOfBirth")
    public  String DateOfBirth;
}
