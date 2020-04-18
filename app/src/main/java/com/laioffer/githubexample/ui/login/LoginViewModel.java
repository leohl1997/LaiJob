package com.laioffer.githubexample.ui.login;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.RemoteResponseListener;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.util.Utils;

public class LoginViewModel extends BaseViewModel<LoginRepository> {

    private RemoteResponseListener<UserInfo> responseListener;

    LoginViewModel(LoginRepository baseRepository) {
        super(baseRepository);
    }

    public void setResponseListener(RemoteResponseListener<UserInfo> responseListener) {
        this.responseListener = responseListener;
    }

    public void login(String userId, String password) {
        if (Utils.isNullOrEmpty(userId)) {
            responseListener.onFailure("Please enter a valid userId!");
            return;
        }
        if (Utils.isNullOrEmpty(password)) {
            responseListener.onFailure("Please enter a valid password!");
            return;
        }
        responseListener.onSuccess(repository.login(userId, password));
    }

}
