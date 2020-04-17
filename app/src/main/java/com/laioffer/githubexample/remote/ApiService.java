package com.laioffer.githubexample.remote;

import com.laioffer.githubexample.remote.response.OnBoardingResponseBody;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ApiService {

    @FormUrlEncoded
    @GET("/Login") // need to know exactly
    Call<OnBoardingResponseBody> login(@Field("userId") String userId,
                                       @Field("password") String password);

}
