package com.laioffer.githubexample.ui.HomeList;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.util.Utils;
import java.util.Calendar;

import java.util.Date;
import java.util.List;


public class HomeListViewModel extends BaseViewModel<HomeListRepository> {
    String keyWord;
    private MutableLiveData<List<Job>> ListJobMutableLiveData;

    HomeListViewModel(HomeListRepository baseRepository) {
        super(baseRepository);
    }
    public MutableLiveData<List<Job>> getListJobMutableLiveData(String keyWord){

        this.keyWord = keyWord;
        ListJobMutableLiveData = repository.searchNearby(keyWord);
        return ListJobMutableLiveData;

        }

}

