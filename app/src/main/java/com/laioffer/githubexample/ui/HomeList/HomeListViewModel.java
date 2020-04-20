package com.laioffer.githubexample.ui.HomeList;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;

public class HomeListViewModel extends BaseViewModel<HomeListRepository> {
    protected HomeListViewModel(HomeListRepository baseRepository) {
        super(baseRepository);
    }
    private MutableLiveData<String> mUserName;
    private String getUserName(){
        if(mUserName == null){
            mUserName = new MutableLiveData<String>();
        }
        return "user";
    }

    // TODO: Implement the ViewModel
}
