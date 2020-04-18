package com.laioffer.githubexample.remote;

import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/login") // need to know exactly
    Call<RemoteResponse<UserInfo>> login(@Field("userId") String userId,
                                         @Field("password") String password);

    @FormUrlEncoded
    @POST("/register") // need to know exactly
    Call<RemoteResponse<UserInfo>> register(@Field("userId") String userId,
                                            @Field("password") String password,
                                            @Field("firstName") String firstName,
                                            @Field("lastName") String lastName);

}
