package com.laioffer.githubexample.ui.map;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.List;

public class MapViewModel extends BaseViewModel<MapRepository> {

   private MutableLiveData<List<Job>> listMutableLiveData = repository.search();

    protected MapViewModel(MapRepository baseRepository) {
        super(baseRepository);
    }

    public MutableLiveData<List<Job>> getListMutableLiveData() {
        return listMutableLiveData;
    }

}
