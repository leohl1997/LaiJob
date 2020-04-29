package com.laioffer.githubexample.ui.editWork;

import com.google.gson.annotations.SerializedName;
import com.laioffer.githubexample.util.Config;

public class EditWorkEvent {
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

    EditWorkEvent(String companyName, String jobTitle, String jobStartDate,String jobEndDate){
        this.userId = Config.userId;
        this.schoolName = Config.schoolName;
        this.startDate = Config.schoolStartData;
        this.endDate = Config.schoolEndData;
        this.Address = Config.address;
        this.DateOfBirth = Config.dataOfBirth;
        this.Email = Config.email;
        this.FirstName = Config.firstName;
        this.LastName = Config.lastName;
        this.JobTitle = jobTitle;
        this.jobEndDate = jobEndDate;
        this.jobStartDate = jobStartDate;
        this.CompanyName = companyName;
        this.PhoneNumber = Config.phone;
    }
}
