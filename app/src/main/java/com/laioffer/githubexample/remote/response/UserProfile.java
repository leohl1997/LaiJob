package com.laioffer.githubexample.remote.response;
import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @SerializedName("education")
    public Education education;
    @SerializedName("work")
    public  Work work;
    @SerializedName("profile")
    public Profile profile;
//    @SerializedName("schoolEndData")
//    public String endDate;
//    @SerializedName("firstName")
//    public String FirstName;
//    @SerializedName("lastName")
//    public String LastName;
//    @SerializedName("phone")
//    public String PhoneNumber;
//    @SerializedName("email")
//    public String Email;
//    @SerializedName("address")
//    public String Address;
//    @SerializedName("dataOfBirth")
//    public String DateOfBirth;
//    @SerializedName("company")
//    public String CompanyName;
//    @SerializedName("job")
//    public String JobTitle;
//    @SerializedName("skill")
//    public String Skill;
//    @SerializedName("jobStartData")
//    public String jobStartDate;
//    @SerializedName("jobEndData")
//    public String jobEndDate;
}
