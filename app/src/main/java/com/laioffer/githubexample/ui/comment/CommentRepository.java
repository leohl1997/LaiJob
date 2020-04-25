package com.laioffer.githubexample.ui.comment;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.remote.response.RemoteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CommentRepository extends BaseRepository {
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
