package com.laioffer.githubexample.ui.map;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.util.config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MapRepository extends BaseRepository {
    MutableLiveData<List<Job>> search(String keyword) {
        MutableLiveData<List<Job>> listMutableLiveData = new MutableLiveData<>();
<<<<<<< HEAD
        Call<RemoteResponse<List<Job>>> call = apiService.search(config.lat, config.lon);
=======
        Call<RemoteResponse<List<Job>>> call = apiService.search(Config.latitude, Config.longitude);
>>>>>>> bc0c1af1071fcf9a78b627c46fc9c842d2395ee1
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
