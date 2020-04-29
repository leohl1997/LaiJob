package com.laioffer.githubexample.ui.favorite;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.util.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class FavoriteJobRepository extends BaseRepository {
    public MutableLiveData<List<Job>> getFavorite() {
        final MutableLiveData<List<Job>> result = new MutableLiveData<>();
        // retrieve user-related data
        Call<RemoteResponse<List<Job>>> call = apiService.getFavorite(Config.userId);
        call.enqueue(new Callback<RemoteResponse<List<Job>>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<List<Job>>> call, Response<RemoteResponse<List<Job>>> response) {
                result.postValue(response.body().response);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<List<Job>>> call, Throwable t) {
                result.postValue(null);
            }
        });

        return result;
    }
}
