package com.laioffer.githubexample.ui.editWork;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.RemoteResponse;

public class editWorkViewModel extends BaseViewModel<EditWorkRepository> {
    protected editWorkViewModel(EditWorkRepository baseRepository) {
        super(baseRepository);
    }
    // TODO: Implement the ViewModel
    private MutableLiveData<EditWorkEvent> editProfileEventMutableLiveData = new MutableLiveData<>();
    private LiveData<RemoteResponse<EditWorkEvent>> responseLiveData = Transformations
            .switchMap(editProfileEventMutableLiveData, repository::editWork);
    private MutableLiveData<String> msgMutableLiveData = new MutableLiveData<>();


    // TODO: Implement the ViewModel

    public void sendWork(EditWorkEvent editWorkEvent){
        if (editWorkEvent.FirstName == null) {
            msgMutableLiveData.postValue("School name can not be empty!");
            return;
        }
        if (editWorkEvent.LastName == null) {
            msgMutableLiveData.postValue("School name can not be empty!");
            return;
        }
        editProfileEventMutableLiveData.postValue(editWorkEvent);
    }
    public LiveData<RemoteResponse<EditWorkEvent>> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<String> getMsgMutableLiveData() {
        return msgMutableLiveData;
    }

}
