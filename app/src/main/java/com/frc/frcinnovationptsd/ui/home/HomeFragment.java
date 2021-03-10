package com.frc.frcinnovationptsd.ui.home;

import android.animation.IntEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Simulator simulator = new Simulator(homeViewModel);
        simulator.simulate();

        final TextView decibelText = root.findViewById(R.id.decibel_home_text);
        final TextView heartRateText = root.findViewById(R.id.heart_rate_home_text);
        final ProgressBar decibelBar = root.findViewById(R.id.decibel_progress_home);
        final ProgressBar heartRateBar = root.findViewById(R.id.heart_rate_progress_home);

        homeViewModel.getDecibel().observe(getViewLifecycleOwner(),
                i -> {
                        decibelText.setText(i.toString());
                        BasicAnimator.GetAnimator(decibelBar, "progress", 250,
                                                    new IntEvaluator(), null, decibelBar.getProgress(), i).start();
                    });
        homeViewModel.getHeartRate().observe(getViewLifecycleOwner(),
                i -> {
                        heartRateText.setText(i.toString());
                        BasicAnimator.GetAnimator(heartRateBar, "progress", 250,
                                                    new IntEvaluator(), null, heartRateBar.getProgress(), i).start();
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