package com.laioffer.githubexample.ui.favorite;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.List;

public class FavoriteJobViewModel extends BaseViewModel<FavoriteJobRepository> {

    protected FavoriteJobViewModel(FavoriteJobRepository baseRepository) {
        super(baseRepository);
    }

    public MutableLiveData<List<Job>> getFavJobLiveData() {
        return repository.getFavorite();
    }

}
