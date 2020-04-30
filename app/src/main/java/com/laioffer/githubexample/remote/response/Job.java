package com.laioffer.githubexample.remote.response;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.ui.comment.Item;

import java.io.Serializable;
import java.util.List;

public class Job extends Item implements Serializable {
    @SerializedName("address")
    public String address;

    @SerializedName("keywords")
    public List<String> keywords;

    @SerializedName("item_id")
    public String itemId;

    @SerializedName("description")
    public String description;

    @SerializedName("type")
    public String type;

    @SerializedName("url")
    public String url;

    @SerializedName("name")
    public String name;

    @SerializedName("company")
    public String company;

    @SerializedName("favorite")
    public boolean favorite;

    @SerializedName("location")
    public Location location;

    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("time")
    public String time;




    //    @SerializedName("lat")
//    public double lat;
//
//    @SerializedName("lon")
//    public double lon;
    public Job(String jobTitle, String location, String company,String postTime, String image_url, String jobDescription, String apply_url) {
        this.name = jobTitle;
        this.company = company;
        this.address = location;
        this.time = postTime;
        this.imageUrl = image_url;
        this.description = jobDescription;
        this.url = apply_url;
    }


    public String getTitle() { return this.name; }
    public String getCompany() { return this.company; }
    public String getAddress() { return this.address; }
    public String getPostTime() { return this.time; }
    public String getImage_url(){ return this.imageUrl;}
    public String getApply_url(){ return this.url;}
    public String getJobDescription(){ return this.description;}





}
