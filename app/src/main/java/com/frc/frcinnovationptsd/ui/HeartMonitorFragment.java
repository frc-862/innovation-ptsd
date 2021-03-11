package com.frc.frcinnovationptsd.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.frc.frcinnovationptsd.R;

public class HeartMonitorFragment extends Fragment {

    public static HomeViewModel viewModel;
    private boolean hasWarned;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.heart_monitor, container, false);

        final TextView rate = root.findViewById(R.id.rate_hr);
        final ImageView state = root.findViewById(R.id.state_hr);
        viewModel.getHeartRate().observe(getViewLifecycleOwner(),
                t -> {
                    rate.setText(t.toString());
                    if(viewModel.isHeartRateHigh() && !hasWarned){
                        state.setImageResource(R.drawable.ic_state_warning_24dp);
                        hasWarned = true;
                    }
                    else if(!viewModel.isHeartRateHigh() && hasWarned){
                        state.setImageResource(R.drawable.ic_state_safe_24dp);
                        hasWarned = false;
                    }
                });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // register listeners
    }
}