package com.laioffer.githubexample.ui.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel extends BaseViewModel<MapRepository> {

    private MutableLiveData<String> searchEvent = new MutableLiveData<>();
    private LiveData<List<Job>> listMutableLiveData =
            Transformations.switchMap(searchEvent, repository::search);
    private MutableLiveData<String> msg = new MutableLiveData<>();
    private ArrayList<Job> savedJob = new ArrayList<>();
    private MutableLiveData<List<Job>> recommendation = repository.recommendation();

    protected MapViewModel(MapRepository baseRepository) {
        super(baseRepository);
    }

    public void setSearchEvent(String query) {
        if (query == null) {
            return;
        }
        msg.postValue("Searching...");
        searchEvent.postValue(query);
    }

    public MutableLiveData<String> getMsg() {
        return msg;
    }

    public LiveData<List<Job>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public ArrayList<Job> getSavedJob() {
        return savedJob;
    }

    public MutableLiveData<List<Job>> getRecommendation() {
        return recommendation;
    }

}
