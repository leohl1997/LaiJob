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

import java.io.Serializable;

public class Job  implements Serializable {
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
    @SerializedName("description")
    private String jobDescription;
    @SerializedName("url")
    private String apply_url;

    /**
     * Constructor
     */
    public Job(String jobTitle, String location, String company,String postTime, String image_url, String jobDescription, String apply_url) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.postTime = postTime;
        this.image_url = image_url;
        this.jobDescription = jobDescription;
        this.apply_url = apply_url;
    }

    /**
     * Getters for private attributes of Event class.
     */
    public String getTitle() { return this.jobTitle; }
    public String getCompany() { return this.company; }
    public String getLocation() { return this.location; }
    public String getPostTime() { return this.postTime; }
    public String getImage_url(){ return this.image_url;}
    public String getApply_url(){ return this.apply_url;}
    public String getJobDescription(){ return this.jobDescription;}

    @BindingAdapter({"image_url"})
    public static void loadImage(ImageView imageView, String image_url) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions().circleCrop())
                .load(image_url)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }
}
