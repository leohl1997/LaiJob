package com.laioffer.githubexample.ui.jobInfo;


import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.List;


public class RecommendationViewModel extends BaseViewModel<RecommendationRepository> {



    RecommendationViewModel(RecommendationRepository baseRepository) {
        super(baseRepository);
    }
    String keyWord;
    private MutableLiveData<List<Job>> ListJobMutableLiveData;


    public MutableLiveData<List<Job>> getListJobMutableLiveData(String keyWord){

        this.keyWord = keyWord;
        ListJobMutableLiveData = repository.recommendation(keyWord);
        return ListJobMutableLiveData;

    }



}
