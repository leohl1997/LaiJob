package com.laioffer.githubexample.remote.response;

import com.google.gson.annotations.SerializedName;

public class OnBoardingResponseBody {

    class UserInfo {
        public String name;
        public String userId;
    }

    public String status;

    @SerializedName("response")
    public UserInfo userInfo;
}
