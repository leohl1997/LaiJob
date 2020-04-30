package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.MutableLiveData;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;

import java.util.ArrayList;
import java.util.List;

public class JobInfoViewModel extends BaseViewModel<JobInfoRepository> {


    private JobInfoRecyclerViewAdapter.RemoteListener remoteListener;

    protected JobInfoViewModel(JobInfoRepository baseRepository) {
        super(baseRepository);
    }

    public void setRemoteListener(JobInfoRecyclerViewAdapter.RemoteListener remoteListener) {
        this.remoteListener = remoteListener;
    }

    public void setJobIdLiveData(String jobId) {
        remoteListener.onCommentEvent(repository.getComment(jobId));
    }

    public void setSaveEvent(SaveEvent saveEvent) {
        remoteListener.onSaveEvent(repository.save(saveEvent));
    }
//
    private ArrayList<Job> tokenRecommendation = new ArrayList<>();
    private final MutableLiveData<List<Job>> ListRecommendationLiveData = repository.getRecommendation();
    private final MutableLiveData<String> tokenResponse = repository.sentToken();

    public MutableLiveData<List<Job>> getListRecommendationJobLiveData() {
        return ListRecommendationLiveData;
    }

    public MutableLiveData<String> getTokenRecommendation() {
        return tokenRecommendation;
    }
}
