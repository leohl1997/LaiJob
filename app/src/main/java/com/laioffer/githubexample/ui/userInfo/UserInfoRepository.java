package com.laioffer.githubexample.ui.userInfo;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserProfile;
import com.laioffer.githubexample.util.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoRepository extends BaseRepository {
    public MutableLiveData<UserProfile> getInfo() {
        final MutableLiveData<UserProfile> result = new MutableLiveData<>();
        // retrieve user-related data
        Call<RemoteResponse<UserProfile>> call = apiService.getInfo(Config.userId);
        call.enqueue(new Callback<RemoteResponse<UserProfile>>() {
            @Override
            public void onResponse(Call<RemoteResponse<UserProfile>> call, Response<RemoteResponse<UserProfile>> response) {
                assert response.body() != null;
                result.postValue(response.body().response);
            }

            @Override
            public void onFailure(Call<RemoteResponse<UserProfile>> call, Throwable t) {
                result.postValue(null);
            }
        });
        return result;
    }
}
