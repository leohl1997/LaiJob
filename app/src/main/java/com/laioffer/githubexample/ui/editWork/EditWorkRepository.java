package com.laioffer.githubexample.ui.editWork;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.RemoteResponse;

import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditWorkRepository extends BaseRepository {
    public MutableLiveData<RemoteResponse<EditWorkEvent>> editWork(EditWorkEvent editWorkEvent) {
        MutableLiveData<RemoteResponse<EditWorkEvent>> responseMutableLiveData = new MutableLiveData<>();
        Call<RemoteResponse<EditWorkEvent>> call = apiService.editWork(editWorkEvent);
        call.enqueue(new Callback<RemoteResponse<EditWorkEvent>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RemoteResponse<EditWorkEvent>> call,
                                   Response<RemoteResponse<EditWorkEvent>> response) {
                responseMutableLiveData.postValue(response.body());
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RemoteResponse<EditWorkEvent>> call, Throwable t) {
                RemoteResponse<EditWorkEvent> errResponse = new RemoteResponse<>();
                errResponse.status = t.getMessage();
                responseMutableLiveData.postValue(errResponse);
            }
        });
        return responseMutableLiveData;
    }
}
