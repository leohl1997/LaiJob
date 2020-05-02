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
    String FirstName;
    @SerializedName("lastName")
    String LastName;
    @SerializedName("phone")
    public String PhoneNumber;
    @SerializedName("email")
    public String Email;
    @SerializedName("address")
    public String Address;
    @SerializedName("dataOfBirth")
    public String DateOfBirth;
    @SerializedName("company")
    public  String CompanyName;
    @SerializedName("job")
    public  String JobTitle;
    @SerializedName("jobStartData")
    public  String jobStartDate;
    @SerializedName("jobEndData")
    public  String jobEndDate;

    EditProfileEvent(String firstName, String lastName, String phoneNumber,String email,String address,String dateOfBirth){
        this.userId = Config.userId;
        this.schoolName = Config.schoolName;
        this.startDate = Config.schoolStartData;
        this.endDate = Config.schoolEndData;
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
