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

    EditEduEvent(String schoolName, String startDate, String endDate){
        this.userId = Config.userId;
        this.schoolName = schoolName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
