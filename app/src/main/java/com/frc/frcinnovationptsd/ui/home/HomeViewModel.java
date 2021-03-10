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
        decibel.setValue(50);
        heartRate.setValue(50);

    }

    public LiveData<Integer> getDecibel() {
        return decibel;
    }

    public void setDecibel(Integer currentDecibel)
    {
        if(currentDecibel < 0 || currentDecibel > 100)
            return;
        decibel.setValue(currentDecibel);
    }

    public LiveData<Integer> getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer currentHeartRate){
        if(currentHeartRate < 0 || currentHeartRate > 100)
            return;
        heartRate.setValue(currentHeartRate);
    }

    // add data bindings
}