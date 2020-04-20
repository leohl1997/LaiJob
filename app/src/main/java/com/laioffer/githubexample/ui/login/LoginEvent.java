package com.laioffer.githubexample.ui.login;

import com.google.gson.annotations.SerializedName;

public class LoginEvent {
    @SerializedName("user_id")
    final String userId;
    @SerializedName("password")
    final String password;

    public LoginEvent(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
