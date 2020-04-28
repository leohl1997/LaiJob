package com.laioffer.githubexample.remote;

import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.HomeList.Job;
import com.laioffer.githubexample.ui.login.LoginEvent;
import com.laioffer.githubexample.ui.register.RegisterEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("jobsearch/login") // need to know exactly
    Call<RemoteResponse<UserInfo>> login(@Body LoginEvent body);

    @POST("jobsearch/register") // need to know exactly
    Call<RemoteResponse<UserInfo>> register(@Body RegisterEvent body);

    @GET("jobsearch/search")
    Call<RemoteResponse<List<Job>>> search(@Query("lat") double latitude,@Query("lon") double longitude, @Query("keyword") String keyWord);

}
