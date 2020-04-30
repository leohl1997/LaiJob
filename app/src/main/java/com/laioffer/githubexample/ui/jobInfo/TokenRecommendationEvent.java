package com.laioffer.githubexample.ui.jobInfo;

import com.google.gson.annotations.SerializedName;

public class TokenRecommendationEvent {
    @SerializedName("user_id")
    public String userId;
    @SerializedName("token")
    public String token;
}
