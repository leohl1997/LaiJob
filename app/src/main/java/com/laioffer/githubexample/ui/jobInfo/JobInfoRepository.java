package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.RemoteResponse;

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
        return saveEvent.job.favorite ? unfavorite(saveEvent) : favorite(saveEvent);
    }

    private MutableLiveData<String> favorite(SaveEvent saveEvent) {
        MutableLiveData<String>  responseLiveDate = new MutableLiveData<>();
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
        MutableLiveData<String>  responseLiveDate = new MutableLiveData<>();
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

    public MutableLiveData<String> comment(CommentEvent commentEvent) {
        MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();
        Call<RemoteResponse<CommentEvent>> call = apiService.sendComment(commentEvent);
        call.enqueue(new Callback<RemoteResponse<CommentEvent>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<CommentEvent>> call,
                                   Response<RemoteResponse<CommentEvent>> response) {
                if (response.code() == 200) {
                    stringMutableLiveData.postValue("Success!");
                } else {
                    stringMutableLiveData.postValue("Error! " + Integer.toString(response.code()));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<CommentEvent>> call, Throwable t) {
                stringMutableLiveData.postValue(t.getMessage());
            }
        });
        return stringMutableLiveData;
    }
}
