package com.laioffer.githubexample.ui.register;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.RemoteResponseListener;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.util.Utils;

public class RegisterViewModel extends BaseViewModel<RegisterRepository> {

    private RemoteResponseListener<UserInfo> responseListener;

    RegisterViewModel(RegisterRepository baseRepository) {
        super(baseRepository);
    }

    public void setResponseListener(RemoteResponseListener<UserInfo> responseListener) {
        this.responseListener = responseListener;
    }

    public void register(String userId, String password, String firstName, String lastName) {
        if (Utils.isNullOrEmpty(userId)) {
            responseListener.onFailure("Please enter a valid userId!");
            return;
        }
        if (Utils.isNullOrEmpty(password)) {
            responseListener.onFailure("Please enter a valid password!");
            return;
        }
        if (Utils.isNullOrEmpty(firstName)) {
            responseListener.onFailure("Please enter a first Name!");
            return;
        }
        if (Utils.isNullOrEmpty(lastName)) {
            responseListener.onFailure("Please enter a last Name!");
            return;
        }
        responseListener.onSuccess(repository.register(userId, password, firstName, lastName));
    }
}
