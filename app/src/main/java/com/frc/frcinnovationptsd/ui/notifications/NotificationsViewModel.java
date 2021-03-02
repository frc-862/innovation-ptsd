package com.frc.frcinnovationptsd.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Integer> progress;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        progress = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
        progress.setValue(0);
    }

    public MutableLiveData<Integer> getProgress(){
        return progress;
    }

    public void setProgress(int newProgress){
        progress.setValue(newProgress);
    }

    // add data bindings
}