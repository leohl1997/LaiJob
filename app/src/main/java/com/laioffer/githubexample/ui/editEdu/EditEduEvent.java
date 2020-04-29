package com.laioffer.githubexample.ui.editEdu;

import com.google.gson.annotations.SerializedName;
import com.laioffer.githubexample.util.Config;

public class EditEduEvent {
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
    public String CompanyName;
    @SerializedName("job")
    public String JobTitle;
    @SerializedName("jobStartData")
    public String jobStartDate;
    @SerializedName("jobEndData")
    public String jobEndDate;

    EditEduEvent(String schoolName, String startDate, String endDate){
        this.userId = Config.userId;
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Address = Config.address;
        this.DateOfBirth = Config.dataOfBirth;
        this.Email = Config.email;
        this.FirstName = Config.firstName;
        this.LastName = Config.lastName;
        this.JobTitle = Config.jobTitle;
        this.jobEndDate = Config.jobEndDate;
        this.jobStartDate = Config.jobStartDate;
        this.CompanyName = Config.company;
        this.PhoneNumber = Config.phone;
    }
}
