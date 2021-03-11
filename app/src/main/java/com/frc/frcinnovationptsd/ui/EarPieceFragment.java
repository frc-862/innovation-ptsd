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

public class EarPieceFragment extends Fragment {

    public static HomeViewModel viewModel;
    private boolean hasWarned;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.ear_piece, container, false);

        final TextView rate = root.findViewById(R.id.rate_ep);
        final ImageView state = root.findViewById(R.id.state_ep);
        viewModel.getDecibel().observe(getViewLifecycleOwner(),
                t -> {
                    rate.setText(t.toString());
                    if(viewModel.isDecibelHigh() && !hasWarned){
                        state.setImageResource(R.drawable.ic_state_warning_24dp);
                        hasWarned = true;
                    }
                    else if(!viewModel.isDecibelHigh() && hasWarned){
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