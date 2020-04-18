package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

public class RemoteResponse<T> {
    @SerializedName("status")
    public String status;

    @SerializedName("response")
    public T response;
}
