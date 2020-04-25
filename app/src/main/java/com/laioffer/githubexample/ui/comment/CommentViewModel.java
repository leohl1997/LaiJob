package com.laioffer.githubexample.ui.comment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;

public class CommentViewModel extends BaseViewModel <CommentRepository> {

    private MutableLiveData<CommentEvent> commentEventMutableLiveData = new MutableLiveData<>();
    private LiveData<String> responseLiveData = Transformations
            .switchMap(commentEventMutableLiveData, repository::comment);
    private MutableLiveData<String> msgMutableLiveData = new MutableLiveData<>();

    protected CommentViewModel(CommentRepository baseRepository) {
        super(baseRepository);
    }

    public void setCommentEventMutableLiveData(CommentEvent commentEvent) {
        if (commentEvent == null) {
            msgMutableLiveData.postValue("Empty Comment Event!");
            return;
        }
        if (commentEvent.rating == 0) {
            msgMutableLiveData.postValue("Please rate first!");
            return;
        }
        if (commentEvent.commentText.isEmpty()) {
            msgMutableLiveData.postValue("Please write comment!");
            return;
        }
        commentEventMutableLiveData.postValue(commentEvent);
    }

    public LiveData<String> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<String> getMsgMutableLiveData() {
        return msgMutableLiveData;
    }
}
