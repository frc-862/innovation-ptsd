package com.frc.frcinnovationptsd;

import android.os.Handler;

import com.frc.frcinnovationptsd.ui.HomeViewModel;

public class Simulator {
    private static boolean instantiated;

    Handler handler;
    Runnable runnable;

    public Simulator(HomeViewModel viewModel){
        handler = new Handler();
        runnable = new Runnable () {
            @Override
            public void run(){
                int decibelRandom = (int)(Math.random() * 10 + Math.random() * -10);
                int heartRateRandom = (int)(Math.random() * 10 + Math.random() * -10);
                viewModel.setDecibel(viewModel.getDecibel().getValue() + decibelRandom);
                viewModel.setHeartRate(viewModel.getHeartRate().getValue() + heartRateRandom);
                handler.postDelayed(this, 1000);
            }
        };
    }

    public void simulate(){
        if(!instantiated){
            handler.post(runnable);
            instantiated = true;
        }
    }
}
