package com.laioffer.githubexample.ui.jobInfo;

import com.google.gson.annotations.SerializedName;
import com.laioffer.githubexample.remote.response.Item;

public class CommentEvent extends Item {
    @SerializedName("user_id")
    public String userId;
    @SerializedName("item_id")
    public String itemId;
    @SerializedName("rating")
    public int rating;
    @SerializedName("context")
    public String commentText;
    @SerializedName("time")
    public String currentTime;

}
