package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.comment.CommentEvent;

import java.util.ArrayList;
import java.util.List;

public class JobInfoViewModel extends BaseViewModel<JobInfoRepository> {

    private MutableLiveData<String> jobIdLiveData = new MutableLiveData<>();
    private LiveData<List<CommentEvent>> commentLiveData =
            Transformations.switchMap(jobIdLiveData, repository::getComment);
    private MutableLiveData<SaveEvent> saveEventLiveData = new MutableLiveData<>();
    private LiveData<String> saveResponse =
            Transformations.switchMap(saveEventLiveData, repository::save);

    protected JobInfoViewModel(JobInfoRepository baseRepository) {
        super(baseRepository);
    }

    public LiveData<List<CommentEvent>> getCommentLiveData() {
        return commentLiveData;
    }

    public void setJobIdLiveData(String jobId) {
        jobIdLiveData.postValue(jobId);
    }

    public void setSaveEvent(SaveEvent saveEvent) {
        saveEventLiveData.postValue(saveEvent);
    }

    public LiveData<String> getSaveResponse() {
        return saveResponse;
    }




}
