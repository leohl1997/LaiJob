package com.laioffer.githubexample.ui.login;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.util.Utils;

public class LoginViewModel extends BaseViewModel<LoginRepository> {

    private final MutableLiveData<LoginEvent> loginEventMutableLiveData = new MutableLiveData<>();
    private final LiveData<RemoteResponse<UserInfo>> remoteResponseMutableLiveData =
            Transformations.switchMap(loginEventMutableLiveData, repository::login);
    private final MutableLiveData<String> errMsgMutableLiveData = new MutableLiveData<>();

    LoginViewModel(LoginRepository baseRepository) {
        super(baseRepository);
    }

    public LiveData<RemoteResponse<UserInfo>> getRemoteResponseMutableLiveData() {
        return remoteResponseMutableLiveData;
    }

    public MutableLiveData<String> getErrMsgMutableLiveData() {
        return errMsgMutableLiveData;
    }

    public void  login(LoginEvent loginEvent) {
        if (Utils.isNullOrEmpty(loginEvent.userId)) {
            errMsgMutableLiveData.setValue("Please enter a valid password!");
            return;
        }
        if (Utils.isNullOrEmpty(loginEvent.password)) {
            errMsgMutableLiveData.setValue("Please enter a valid password!");
            return;
        }
        loginEventMutableLiveData.setValue(loginEvent);
    }

}
