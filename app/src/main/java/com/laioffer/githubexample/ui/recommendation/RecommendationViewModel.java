package com.laioffer.githubexample.ui.recommendation;


import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.List;


public class RecommendationViewModel extends BaseViewModel<RecommendationRepository> {



    RecommendationViewModel(RecommendationRepository baseRepository) {
        super(baseRepository);
    }
    private MutableLiveData<List<Job>> ListJobMutableLiveData;


    public MutableLiveData<List<Job>> getListJobMutableLiveData(){

        ListJobMutableLiveData = repository.recommendation();
        return ListJobMutableLiveData;

    }



}