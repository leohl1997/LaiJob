package com.laioffer.githubexample.ui.HomeList;


import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.List;


public class HomeListViewModel extends BaseViewModel<HomeListRepository> {

    private final MutableLiveData<String> tokenResponse = repository.sentToken();

    HomeListViewModel(HomeListRepository baseRepository) {
        super(baseRepository);
    }
    String keyWord;
    private MutableLiveData<List<Job>> ListJobMutableLiveData;


    public MutableLiveData<List<Job>> getListJobMutableLiveData(String keyWord){

        this.keyWord = keyWord;
        ListJobMutableLiveData = repository.searchNearby(keyWord);
        return ListJobMutableLiveData;

    }

    public MutableLiveData<String> getTokenResponse() {
        return tokenResponse;
    }

}

