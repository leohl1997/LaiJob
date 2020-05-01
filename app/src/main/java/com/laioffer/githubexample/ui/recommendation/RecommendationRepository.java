package com.laioffer.githubexample.ui.recommendation;




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

public class RecommendationRepository extends BaseRepository {

    MutableLiveData<List<Job>> recommendation() {
        MutableLiveData<List<Job>> listMutableLiveData = new MutableLiveData<>();
        Call<RemoteResponse<List<Job>>> call = apiService
                .getRecommendation(Config.latitude, Config.longitude, Config.userId);
        call.enqueue(new Callback<RemoteResponse<List<Job>>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<List<Job>>> call, Response<RemoteResponse<List<Job>>> response) {
                if (response.code() == 200) {
                    RemoteResponse<List<Job>> list = response.body();
                    listMutableLiveData.postValue(response.body().response);
                } else {
                    listMutableLiveData.postValue(null);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<List<Job>>> call, Throwable t) {
                listMutableLiveData.postValue(null);
            }
        });
        return listMutableLiveData;

    }



}