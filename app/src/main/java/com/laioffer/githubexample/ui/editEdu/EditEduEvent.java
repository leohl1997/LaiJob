package com.laioffer.githubexample.ui.editEdu;

import com.google.gson.annotations.SerializedName;

public class EditEduEvent {
    @SerializedName("schoolName")
    public String schoolName;
    @SerializedName("schoolStartData")
    public String startDate;
    @SerializedName("schoolEndData")
    public String endDate;

    EditEduEvent(String schoolName, String startDate, String endDate){
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
