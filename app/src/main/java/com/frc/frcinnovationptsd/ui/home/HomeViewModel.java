package com.frc.frcinnovationptsd.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> decibel;
    private MutableLiveData<Integer> heartRate;

    public HomeViewModel() {
        decibel = new MutableLiveData<>();
        heartRate = new MutableLiveData<>();
        decibel.setValue(0);
        heartRate.setValue(0);
    }

    public LiveData<Integer> getDecibel() {
        return decibel;
    }

    public void setDecibel(Integer currentDecibel){
        decibel.setValue(currentDecibel);
    }

    public LiveData<Integer> getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer currentHeartRate){
        heartRate.setValue(currentHeartRate);
    }

    // add data bindings
}