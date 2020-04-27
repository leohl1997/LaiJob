package com.laioffer.githubexample.ui.editEdu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.Education;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.comment.CommentEvent;

public class EditEduViewModel extends BaseViewModel<EditEduRepository> {

    private MutableLiveData<EditEduEvent> editEduEventMutableLiveData = new MutableLiveData<>();
    private LiveData<RemoteResponse<EditEduEvent>> responseLiveData = Transformations
            .switchMap(editEduEventMutableLiveData, repository::editEdu);
    private MutableLiveData<String> msgMutableLiveData = new MutableLiveData<>();


    protected EditEduViewModel(EditEduRepository baseRepository) {
        super(baseRepository);
    }
    // TODO: Implement the ViewModel

    public void sendEdu(EditEduEvent editEduEvent){
        if (editEduEvent.schoolName == null) {
            msgMutableLiveData.postValue("School name can not be empty!");
            return;
        }
        if (editEduEvent.startDate == null) {
            msgMutableLiveData.postValue("School name can not be empty!");
            return;
        }
       editEduEventMutableLiveData.setValue(editEduEvent);
    }
    public LiveData<RemoteResponse<EditEduEvent>> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<String> getMsgMutableLiveData() {
        return msgMutableLiveData;
    }
}
