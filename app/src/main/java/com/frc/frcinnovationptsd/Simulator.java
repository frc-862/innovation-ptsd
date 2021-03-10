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
                int decibelRandom = (int)(Math.random() * 10 + Math.random() * -10);
                int heartRateRandom = (int)(Math.random() * 10 + Math.random() * -10);
                viewModel.setDecibel(viewModel.getDecibel().getValue() + decibelRandom);
                viewModel.setHeartRate(viewModel.getHeartRate().getValue() + heartRateRandom);

                Log.println(Log.ASSERT, "Runnable", "ran");
                handler.postDelayed(this, 2000);
            }
        };

        handler.post(runnable);
    }

    public void simulate(){
        runnable.run();
    }
}
