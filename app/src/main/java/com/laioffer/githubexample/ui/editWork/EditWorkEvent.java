package com.laioffer.githubexample.ui.editWork;

import com.google.gson.annotations.SerializedName;

public class EditWorkEvent {
    @SerializedName("company")
    public static String CompanyName;
    @SerializedName("job")
    public static String JobTitle;
    @SerializedName("jobStartData")
    public static String StartDate;
    @SerializedName("jobEndData")
    public static String EndDate;
}
