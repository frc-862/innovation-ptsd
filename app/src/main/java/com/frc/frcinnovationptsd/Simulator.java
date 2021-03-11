package com.frc.frcinnovationptsd;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.frc.frcinnovationptsd.ui.home.HomeViewModel;

public class Simulator {
    Handler handler;
    Runnable runnable;

    public Simulator(HomeViewModel viewModel){
        handler = new Handler();
        runnable = new Runnable () {
            @Override
            public void run(){
                int decibelRandom = (int)(Math.random() * 20 + Math.random() * -0);
                int heartRateRandom = (int)(Math.random() * 20 + Math.random() * -0);
                viewModel.setDecibel(viewModel.getDecibel().getValue() + decibelRandom);
                viewModel.setHeartRate(viewModel.getHeartRate().getValue() + heartRateRandom);
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
    }

    public void simulate(){

    }
}
