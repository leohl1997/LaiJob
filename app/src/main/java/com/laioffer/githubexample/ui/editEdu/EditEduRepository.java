package com.laioffer.githubexample.ui.editEdu;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.Education;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class EditEduRepository extends BaseRepository {
    public MutableLiveData<RemoteResponse<EditEduEvent>> editEdu(EditEduEvent editEduEvent) {
        MutableLiveData<RemoteResponse<EditEduEvent>> responseMutableLiveData = new MutableLiveData<>();
        Call<RemoteResponse<EditEduEvent>> call = apiService.editEdu(editEduEvent);
        call.enqueue(new Callback<RemoteResponse<EditEduEvent>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<EditEduEvent>> call,
                                   Response<RemoteResponse<EditEduEvent>> response) {
                responseMutableLiveData.setValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<EditEduEvent>> call, Throwable t) {
                RemoteResponse<EditEduEvent> errResponse = new RemoteResponse<>();
                errResponse.status = t.getMessage();
                responseMutableLiveData.setValue(errResponse);
            }
        });
        return responseMutableLiveData;
    }
}
