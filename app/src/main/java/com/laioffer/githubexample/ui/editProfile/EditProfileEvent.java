package com.laioffer.githubexample.ui.editProfile;

import com.google.gson.annotations.SerializedName;

public class EditProfileEvent {
    @SerializedName("first_name")
    public static String FirstName;
    @SerializedName("last_name")
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
