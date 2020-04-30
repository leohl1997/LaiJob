package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.MutableLiveData;
import com.laioffer.githubexample.base.BaseRepository;

import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.ui.comment.CommentEvent;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class JobInfoRepository extends BaseRepository {
    public MutableLiveData<List<CommentEvent>> getComment(String jobId) {
        MutableLiveData<List<CommentEvent>> responseLiveData = new MutableLiveData<>();
        Call<RemoteResponse<List<CommentEvent>>> call = apiService.getComment(jobId);
        call.enqueue(new Callback<RemoteResponse<List<CommentEvent>>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<List<CommentEvent>>> call, Response<RemoteResponse<List<CommentEvent>>> response) {
                if (response.code() == 200) {
                    responseLiveData.postValue(response.body().response);
                } else {
                    responseLiveData.postValue(null);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<List<CommentEvent>>> call, Throwable t) {
                responseLiveData.postValue(null);
            }
        });
        return responseLiveData;
    }

    public MutableLiveData<List<Job>> getRecommendation() {
        MutableLiveData<List<Job>> listRecommendationLiveData = new MutableLiveData<>();

        Call<RemoteResponse<List<Job>>> call = apiService.getRecommendation(Config.latitude, Config.longitude, Config.userId);
        call.enqueue(new Callback<RemoteResponse<List<Job>>>() {
            @Override
            public void onResponse(Call<RemoteResponse<List<Job>>> call, Response<RemoteResponse<List<Job>>> response) {
                listRecommendationLiveData.postValue(response.body().response);
            }

            @Override
            public void onFailure(Call<RemoteResponse<List<Job>>> call, Throwable t) {
                listRecommendationLiveData.postValue(null);
            }
        });

        return listRecommendationLiveData;
    }




    public MutableLiveData<String> save(SaveEvent saveEvent) {
        return saveEvent.job.favorite ? unfavorite(saveEvent) : favorite(saveEvent);
    }

    private MutableLiveData<String> favorite(SaveEvent saveEvent) {
        MutableLiveData<String> responseLiveDate = new MutableLiveData<>();
        Call<RemoteResponse<SaveEvent>> call = apiService.favorite(saveEvent);
        call.enqueue(new Callback<RemoteResponse<SaveEvent>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<SaveEvent>> call, Response<RemoteResponse<SaveEvent>> response) {
                if (response.code() == 200) {
                    responseLiveDate.postValue("Save Success!");
                } else {
                    responseLiveDate.postValue("Error");
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<SaveEvent>> call, Throwable t) {
                responseLiveDate.postValue("Error");
            }
        });
        return responseLiveDate;
    }

    private MutableLiveData<String> unfavorite(SaveEvent saveEvent) {
        MutableLiveData<String> responseLiveDate = new MutableLiveData<>();
        Call<RemoteResponse<SaveEvent>> call = apiService.unfavorite(saveEvent);
        call.enqueue(new Callback<RemoteResponse<SaveEvent>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<SaveEvent>> call, Response<RemoteResponse<SaveEvent>> response) {
                if (response.code() == 200) {
                    responseLiveDate.postValue("Unsave Success!");
                } else {
                    responseLiveDate.postValue("Error");
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<SaveEvent>> call, Throwable t) {
                responseLiveDate.postValue("Error");
            }
        });
        return responseLiveDate;
    }

