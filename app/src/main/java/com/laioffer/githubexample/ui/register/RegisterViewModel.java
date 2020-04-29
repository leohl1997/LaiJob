package com.laioffer.githubexample.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.remote.response.UserProfile;
import com.laioffer.githubexample.util.Utils;

public class RegisterViewModel extends BaseViewModel<RegisterRepository> {

    private final MutableLiveData<RegisterEvent>
            registerEventMutableLiveData = new MutableLiveData<>();
    private final LiveData<RemoteResponse<UserInfo>> remoteResponseLiveData =
            Transformations.switchMap(registerEventMutableLiveData, repository::register);
    private final MutableLiveData<String> errMsgMutableLiveData = new MutableLiveData<>();

    RegisterViewModel(RegisterRepository baseRepository) {
        super(baseRepository);
    }

    public MutableLiveData<RegisterEvent> getRegisterEventMutableLiveData() {
        return registerEventMutableLiveData;
    }

    public LiveData<RemoteResponse<UserInfo>> getRemoteResponseLiveData() {
        return remoteResponseLiveData;
    }

    public MutableLiveData<String> getErrMsgMutableLiveData() {
        return errMsgMutableLiveData;
    }


    public void register(RegisterEvent registerEvent) {
        if (Utils.isNullOrEmpty(registerEvent.userId)) {
            errMsgMutableLiveData.setValue("Please enter a valid userId!");
            return;
        }
        if (Utils.isNullOrEmpty(registerEvent.password)) {
            errMsgMutableLiveData.setValue("Please enter a valid password!");
            return;
        }
        if (Utils.isNullOrEmpty(registerEvent.firstName)) {
            errMsgMutableLiveData.setValue("Please enter a first Name!");
            return;
        }
        if (Utils.isNullOrEmpty(registerEvent.lastName)) {
            errMsgMutableLiveData.setValue("Please enter a last Name!");
            return;
        }
        registerEventMutableLiveData.setValue(registerEvent);
    }
}
