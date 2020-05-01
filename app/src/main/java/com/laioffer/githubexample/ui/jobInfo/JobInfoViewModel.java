package com.laioffer.githubexample.ui.jobInfo;

import com.laioffer.githubexample.base.BaseViewModel;

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

    public void setCommentEventMutableLiveData(CommentEvent commentEvent) {
        remoteListener.onSendEvent(repository.comment(commentEvent));
    }


}
