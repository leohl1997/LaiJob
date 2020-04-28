package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.ui.comment.CommentEvent;

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


}
