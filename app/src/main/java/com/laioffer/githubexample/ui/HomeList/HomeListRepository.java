package com.laioffer.githubexample.ui.HomeList;



import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.BaseResponse;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class HomeListRepository extends BaseRepository {
    public MutableLiveData<List<Job>> searchNearby(String keyWord) {
        final MutableLiveData<List<Job>> result = new MutableLiveData<>();
        // retrieve user-related data
        Call<RemoteResponse<List<Job>>> call = apiService.search(37.38,-122.08, keyWord);
        call.enqueue(new Callback<RemoteResponse<List<Job>>>() {
            @Override
            public void onResponse(Call<RemoteResponse<List<Job>>> call, Response<RemoteResponse<List<Job>>> response) {
                result.postValue(response.body().response);
            }

            @Override
            public void onFailure(Call<RemoteResponse<List<Job>>> call, Throwable t) {
                result.postValue(null);
            }
        });

        return result;
    }

    public MutableLiveData<String> sentToken() {
        MutableLiveData<String> result = new MutableLiveData<>();
        if (Utils.isNullOrEmpty(Config.userId) || Utils.isNullOrEmpty(Config.token)) {
            return result;
        }
        TokenEvent tokenEvent = new TokenEvent();
        tokenEvent.token = Config.token;
        tokenEvent.userId = Config.userId;
        Call<BaseResponse> call = apiService.sendToken(tokenEvent);
        call.enqueue(new Callback<BaseResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    result.postValue("Success");
                } else {
                    result.postValue("Fail");
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                result.postValue(t.getMessage());
            }
        });
        return result;
    }

}