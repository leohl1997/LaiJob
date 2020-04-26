package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.ui.comment.CommentEvent;

import java.util.List;

public class JobInfoViewModel extends BaseViewModel<JobInfoRepository> {
    private MutableLiveData<String> jobIdLiveData = new MutableLiveData<>();
    private LiveData<List<CommentEvent>> commentLiveData =
            Transformations.switchMap(jobIdLiveData, repository::getComment);

    protected JobInfoViewModel(JobInfoRepository baseRepository) {
        super(baseRepository);
    }

    public LiveData<List<CommentEvent>> getCommentLiveData() {
        return commentLiveData;
    }

    public void setJobIdLiveData(String jobId) {
        jobIdLiveData.postValue(jobId);
    }

}
