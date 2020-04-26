package com.laioffer.githubexample.ui.jobInfo;

import com.google.gson.annotations.SerializedName;
import com.laioffer.githubexample.remote.response.Job;

public class SaveEvent {
    @SerializedName("user_id")
    public String userId;
    @SerializedName("favorite")
    public Job job;
}
