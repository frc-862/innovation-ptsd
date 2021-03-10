package com.frc.frcinnovationptsd.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.frc.frcinnovationptsd.Constants;

import java.util.EventListener;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Integer> decibel;
    private MutableLiveData<Integer> heartRate;

    private Runnable decibelCheckRunnable;
    private Runnable heartRateCheckRunnable;

    private boolean hasWarnDecibel, hasWarnHeartRate;

    public HomeViewModel() {
        decibel = new MutableLiveData<>();
        heartRate = new MutableLiveData<>();
        decibel.setValue(50);
        heartRate.setValue(50);

    }

    public LiveData<Integer> getDecibel() {
        return decibel;
    }

    public void setDeviceEventListener(Runnable decibelNotification, Runnable heartRateNotification) {
        decibelCheckRunnable = decibelNotification;
        heartRateCheckRunnable = heartRateNotification;
    }

    public void setDecibel(Integer currentDecibel)
    {
        if(isDecibelHigh() && !hasWarnDecibel){
            decibelCheckRunnable.run();
            hasWarnDecibel = true;
        }
        else if(currentDecibel < 0)
            return;
        else if(!isDecibelHigh())
            hasWarnDecibel = false;
        decibel.setValue(currentDecibel);
    }

    public LiveData<Integer> getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer currentHeartRate){
        if(isHeartRateHigh() && !hasWarnHeartRate){
            heartRateCheckRunnable.run();
            hasWarnHeartRate = true;
        }
        else if(currentHeartRate < 0)
            return;
        else if(!isHeartRateHigh())
            hasWarnHeartRate = false;
        heartRate.setValue(currentHeartRate);
    }

    public boolean isDecibelHigh(){
        return decibel.getValue() >= Constants.MAX_DECIBEL;
    }

    public boolean isHeartRateHigh(){
        return heartRate.getValue() >= Constants.MAX_HEART_RATE;
    }
    // add data bindings
}