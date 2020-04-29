package com.laioffer.githubexample.ui.editProfile;

import com.google.gson.annotations.SerializedName;
import com.laioffer.githubexample.util.Config;

public class EditProfileEvent {
    @SerializedName("user_id")
    public String userId;
    @SerializedName("schoolName")
    public String schoolName;
    @SerializedName("schoolStartData")
    public String startDate;
    @SerializedName("schoolEndData")
    public String endDate;
    @SerializedName("firstName")
    public String FirstName;
    @SerializedName("lastName")
    public String LastName;
    @SerializedName("phone")
    public String PhoneNumber;
    @SerializedName("email")
    public String Email;
    @SerializedName("address")
    public String Address;
    @SerializedName("dataOfBirth")
    public String DateOfBirth;
    @SerializedName("company")
    public static String CompanyName;
    @SerializedName("job")
    public static String JobTitle;
    @SerializedName("jobStartData")
    public static String jobStartDate;
    @SerializedName("jobEndData")
    public static String jobEndDate;

    EditProfileEvent(String firstName, String lastName, String phoneNumber,String email,String address,String dateOfBirth){
        this.userId = Config.userId;
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Address = address;
        this.DateOfBirth = dateOfBirth;
        this.Email = email;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.JobTitle = Config.jobTitle;
        this.jobEndDate = Config.jobEndDate;
        this.jobStartDate = Config.jobStartDate;
        this.CompanyName = Config.company;
        this.PhoneNumber = phoneNumber;
    }
}
