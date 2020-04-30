package com.laioffer.githubexample.ui.userInfo;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.UserProfile;

public class UserInfoViewModel extends BaseViewModel<UserInfoRepository> {
    UserInfoViewModel(UserInfoRepository baseRepository) {
        super(baseRepository);
    }
   // private final MutableLiveData<UserProfile> UserInfoMutableLiveData = repository.getInfo();
    public MutableLiveData<UserProfile> getUserInfoMutableLiveData(){
        return repository.getInfo();
    }
    // TODO: Implement the ViewModel
}
