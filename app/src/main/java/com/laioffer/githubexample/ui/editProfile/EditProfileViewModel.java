package com.laioffer.githubexample.ui.editProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.remote.response.RemoteResponse;

public class EditProfileViewModel extends BaseViewModel<EditProfileRepository> {

    public EditProfileViewModel(EditProfileRepository baseRepository) {
        super(baseRepository);
    }

    private MutableLiveData<EditProfileEvent> editProfileEventMutableLiveData = new MutableLiveData<>();
    private LiveData<RemoteResponse<EditProfileEvent>> responseLiveData = Transformations
            .switchMap(editProfileEventMutableLiveData, repository::editProfile);
    private MutableLiveData<String> msgMutableLiveData = new MutableLiveData<>();


    // TODO: Implement the ViewModel

    public void sendProfile(EditProfileEvent editProfileEvent){
        if (editProfileEvent.FirstName == null) {
            msgMutableLiveData.postValue("School name can not be empty!");
            return;
        }
        if (editProfileEvent.LastName == null) {
            msgMutableLiveData.postValue("School name can not be empty!");
            return;
        }
        editProfileEventMutableLiveData.postValue(editProfileEvent);
    }
    public LiveData<RemoteResponse<EditProfileEvent>> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<String> getMsgMutableLiveData() {
        return msgMutableLiveData;
    }
}
