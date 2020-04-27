package com.laioffer.githubexample.ui.comment;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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
