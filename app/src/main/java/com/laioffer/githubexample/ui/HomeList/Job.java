package com.laioffer.githubexample.ui.HomeList;


import android.widget.ImageView;

import com.laioffer.githubexample.R;
import com.google.gson.annotations.SerializedName;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;

public class Job {
    /**
     * All data for a job.
     */
    @SerializedName("name")
    private String jobTitle;
    @SerializedName("company")
    private String company;
    @SerializedName("address")
    private String location;
    @SerializedName("time")
    private String postTime;
    @SerializedName("image_url")
    private String image_url;

    /**
     * Constructor
     */
    public Job(String title, String address, String description,String postTime,String image_url) {
        this.jobTitle = title;
        this.company = address;
        this.location = description;
        this.postTime = postTime;
        this.image_url = image_url;
    }

    /**
     * Getters for private attributes of Event class.
     */
    public String getTitle() { return this.jobTitle; }
    public String getCompany() { return this.company; }
    public String getLocation() { return this.location; }
    public String getPostTime() { return this.postTime; }
    public String getImage_url(){ return this.image_url;}



    @BindingAdapter({"image_url"})
    public static void loadImage(ImageView imageView, String image_url) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions().circleCrop())
                .load(image_url)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }
}
