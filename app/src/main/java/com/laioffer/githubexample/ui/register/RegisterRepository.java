package com.laioffer.githubexample.ui.register;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RegisterRepository extends BaseRepository {
    public MutableLiveData<RemoteResponse<UserInfo>> register
            (String userId, String password, String firstName, String lastName) {
        MutableLiveData<RemoteResponse<UserInfo>> responseMutableLiveData = new MutableLiveData<>();
        Call<RemoteResponse<UserInfo>> call =
                apiService.register(userId, password, firstName, lastName);
        call.enqueue(new Callback<RemoteResponse<UserInfo>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<UserInfo>> call,
                                   Response<RemoteResponse<UserInfo>> response) {
                responseMutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<UserInfo>> call, Throwable t) {
                RemoteResponse<UserInfo> errResponse = new RemoteResponse<>();
                errResponse.status = t.getMessage();
                responseMutableLiveData.setValue(errResponse);
            }
        });
        return responseMutableLiveData;
    }

}
