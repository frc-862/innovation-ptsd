package com.frc.frcinnovationptsd.ui.home;

import android.animation.IntEvaluator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.frc.frcinnovationptsd.BasicAnimator;
import com.frc.frcinnovationptsd.R;
import com.frc.frcinnovationptsd.Simulator;

public class HomeFragment extends Fragment {

    public static HomeViewModel homeViewModel;

    private boolean isDecibelWarned, isHeartRateWarned;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Simulator simulator = new Simulator(homeViewModel);
        simulator.simulate();

        final TextView decibelText = root.findViewById(R.id.decibel_home_text);
        final TextView heartRateText = root.findViewById(R.id.heart_rate_home_text);
        final ProgressBar decibelBar = root.findViewById(R.id.decibel_progress_home);
        final ProgressBar heartRateBar = root.findViewById(R.id.heart_rate_progress_home);
        final ImageView decibelState = root.findViewById(R.id.decibel_status_home);
        final ImageView heartRateState = root.findViewById(R.id.heart_rate_status_home);

        homeViewModel.getDecibel().observe(getViewLifecycleOwner(),
                i -> {
                        decibelText.setText(i.toString());
                        BasicAnimator.GetAnimator(decibelBar, "progress", 250,
                                                    new IntEvaluator(), null, decibelBar.getProgress(), i).start();
                        if(homeViewModel.isDecibelHigh() && !isDecibelWarned){

                            decibelState.setImageResource(R.drawable.ic_state_warning_24dp);
                            isDecibelWarned = true;
                        }
                        else if(!homeViewModel.isDecibelHigh() && isDecibelWarned){
                            decibelState.setImageResource(R.drawable.ic_state_safe_24dp);
                            isHeartRateWarned = false;
                        }
                    });
        homeViewModel.getHeartRate().observe(getViewLifecycleOwner(),
                i -> {
                        heartRateText.setText(i.toString());
                        BasicAnimator.GetAnimator(heartRateBar, "progress", 250,
                                                    new IntEvaluator(), null, heartRateBar.getProgress(), i).start();
                        if(homeViewModel.isHeartRateHigh() && !isHeartRateWarned){
                            heartRateState.setImageResource(R.drawable.ic_state_warning_24dp);
                            isHeartRateWarned = true;
                        } else if(!homeViewModel.isHeartRateHigh() && isDecibelWarned){
                            heartRateState.setImageResource(R.drawable.ic_state_safe_24dp);
                            isHeartRateWarned = false;
                        }
                    });


        Button button = root.findViewById(R.id.therapy_home);
        button.setOnClickListener(b -> {homeViewModel.setDecibel(homeViewModel.getDecibel().getValue() + 10);
                                        homeViewModel.setHeartRate(homeViewModel.getHeartRate().getValue() + 10);});
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // register listeners
    }
}