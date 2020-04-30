package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

public class Work {
    @SerializedName("company")
    public  String CompanyName;
    @SerializedName("job")
    public  String JobTitle;
    @SerializedName("jobStartData")
    public  String StartDate;
    @SerializedName("jobEndData")
    public  String EndDate;
}
