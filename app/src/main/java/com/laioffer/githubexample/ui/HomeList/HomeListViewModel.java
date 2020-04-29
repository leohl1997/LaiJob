package com.laioffer.githubexample.ui.HomeList;


import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.List;


public class HomeListViewModel extends BaseViewModel<HomeListRepository> {
    private final MutableLiveData<List<Job>> ListJobMutableLiveData = repository.searchNearby();
    private final MutableLiveData<String> tokenResponse = repository.sentToken();

    HomeListViewModel(HomeListRepository baseRepository) {
        super(baseRepository);
    }
    public MutableLiveData<List<Job>> getListJobMutableLiveData(){
        return ListJobMutableLiveData;
    }

    public MutableLiveData<String> getTokenResponse() {
        return tokenResponse;
    }

}

