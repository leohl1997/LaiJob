package com.laioffer.githubexample.remote;

import com.laioffer.githubexample.remote.response.Education;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.comment.CommentEvent;

import com.laioffer.githubexample.ui.editEdu.EditEduEvent;
import com.laioffer.githubexample.ui.editProfile.EditProfileEvent;
import com.laioffer.githubexample.ui.editWork.EditWorkEvent;
import com.laioffer.githubexample.ui.editWork.EditWorkFragment;

import com.laioffer.githubexample.ui.jobInfo.SaveEvent;

import com.laioffer.githubexample.ui.login.LoginEvent;
import com.laioffer.githubexample.ui.register.RegisterEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("jobsearch/login") // need to know exactly
    Call<RemoteResponse<UserInfo>> login(@Body LoginEvent body);

    @POST("jobsearch/register") // need to know exactly
    Call<RemoteResponse<UserInfo>> register(@Body RegisterEvent body);

    @GET("jobsearch/search")
    Call<RemoteResponse<List<Job>>> search(@Query("lat") double lat, @Query("lon") double lon);

    @POST("jobsearch/comment")
    Call<RemoteResponse<CommentEvent>> sendComment(@Body CommentEvent commentEvent);


    @POST("jobsearch/user")
    Call<RemoteResponse<EditEduEvent>> editEdu(@Body EditEduEvent editEduEvent);

    @POST("jobsearch/user")
    Call<RemoteResponse<EditProfileEvent>> editProfile(@Body EditProfileEvent editProfileEvent);

    @POST("jobsearch/user")
    Call<RemoteResponse<EditWorkEvent>> editWork(@Body EditWorkEvent editWorkEvent);

    @GET("jobsearch/comment")
    Call<RemoteResponse<List<CommentEvent>>> getComment(@Query("item_id") String jobId);

    @GET("jobsearch/history")
    Call<RemoteResponse<List<Job>>> getFavorite(@Query("user_id") String userId);

    @GET("jobsearch/recommendation")
    Call<RemoteResponse<List<Job>>> getRecommendation(@Query("lat") double lat,
                                                      @Query("lon") double lon,
                                                      @Query("user_id") String userId);

    @POST("jobsearch/history")
    Call<RemoteResponse<SaveEvent>> favorite(@Body SaveEvent saveEvent);

    @HTTP(method = "DELETE", path = "jobsearch/history", hasBody = true)
    Call<RemoteResponse<SaveEvent>> unfavorite(@Body SaveEvent saveEvent);



}
