package com.frc.frcinnovationptsd.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.frc.frcinnovationptsd.R;

public class NotificationsFragment extends Fragment {

    public static NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final ProgressBar progressBar = root.findViewById(R.id.notification_progress_bar);

        notificationsViewModel.getProgress().observe(getViewLifecycleOwner(), t -> progressBar.setProgress(t));

        final Button button = root.findViewById(R.id.notification_button);
        button.setOnClickListener(b -> notificationsViewModel.setProgress(progressBar.getProgress() + 5));

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // register listeners
    }
}