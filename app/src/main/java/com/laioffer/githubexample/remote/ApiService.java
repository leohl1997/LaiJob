package com.laioffer.githubexample.remote;

import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.login.LoginEvent;
import com.laioffer.githubexample.ui.register.RegisterEvent;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("jobsearch/login") // need to know exactly
    Call<RemoteResponse<UserInfo>> login(@Body LoginEvent body);

    @POST("jobsearch/register") // need to know exactly
    Call<RemoteResponse<UserInfo>> register(@Body RegisterEvent body);

}
