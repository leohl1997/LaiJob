package com.laioffer.githubexample.ui.editProfile;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.RemoteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class EditProfileRepository extends BaseRepository {
    public MutableLiveData<RemoteResponse<EditProfileEvent>> editProfile(EditProfileEvent editProfileEvent) {
        MutableLiveData<RemoteResponse<EditProfileEvent>> responseMutableLiveData = new MutableLiveData<>();
        Call<RemoteResponse<EditProfileEvent>> call = apiService.editProfile(editProfileEvent);
        call.enqueue(new Callback<RemoteResponse<EditProfileEvent>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<EditProfileEvent>> call,
                                   Response<RemoteResponse<EditProfileEvent>> response) {
                responseMutableLiveData.postValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<EditProfileEvent>> call, Throwable t) {
                RemoteResponse<EditProfileEvent> errResponse = new RemoteResponse<>();
                errResponse.status = t.getMessage();
                responseMutableLiveData.postValue(errResponse);
            }
        });
        return responseMutableLiveData;
    }
}
