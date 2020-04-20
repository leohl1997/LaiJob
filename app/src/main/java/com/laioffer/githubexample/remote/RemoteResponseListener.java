package com.laioffer.githubexample.remote;

import androidx.lifecycle.LiveData;

import com.laioffer.githubexample.remote.response.RemoteResponse;

public interface RemoteResponseListener<T> {
    void onStart();
    void onSuccess(LiveData<RemoteResponse<T>> response);
    void onFailure(String msg);
}
