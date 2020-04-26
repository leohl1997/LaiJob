package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.ui.comment.CommentEvent;

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

    public MutableLiveData<String> save(SaveEvent saveEvent) {
        return null;
    }

    private MutableLiveData<String> favorite(SaveEvent saveEvent) {
        return null;
    }

    private MutableLiveData<String> unfavorite(SaveEvent saveEvent) {
        return null;
    }
}
