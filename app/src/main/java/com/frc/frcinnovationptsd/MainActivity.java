package com.frc.frcinnovationptsd;

import android.animation.FloatEvaluator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.frc.frcinnovationptsd.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseCloud database;
    private HomeViewModel homeViewModel;

    Map<String, String> testUser = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testUser.put("name", "bob");
        testUser.put("heart", "32");
        testUser.put("pressure", "90");
        database = new FirebaseCloud("bob");

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        setContentView(com.frc.frcinnovationptsd.R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_home , R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        DisplayMetrics displaySize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaySize);
        int height = displaySize.heightPixels;
        int width = displaySize.widthPixels;

        // pop up window
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View copingView = inflater.inflate(R.layout.coping_heart_rate, null);

        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setHeight(height);
        popupWindow.setWidth(width);
        popupWindow.setContentView(copingView);

        final Button copingButton = findViewById(R.id.coping_home);
        final Button exitCopingButton = copingView.findViewById(R.id.exit_coping);
        exitCopingButton.setOnClickListener(i -> popupWindow.dismiss());
        copingButton.setOnClickListener(i -> {
            popupWindow.showAtLocation(copingView, Gravity.CENTER, 0, 0);
        });
        final View welcomeText = findViewById(R.id.text_home);
        BasicAnimator.AnimateMultipleParallel(
                BasicAnimator.GetAnimator(welcomeText, "alpha", 3000,
                        new FloatEvaluator(), null, 0, 1),
                BasicAnimator.GetAnimator(welcomeText, "translationY", 3000,
                        new FloatEvaluator(), new DecelerateInterpolator(), -250, 0)
        );
        // [end] pop up window

        // notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder decibelNotification = new NotificationCompat.Builder(this)
                .setContentTitle("High Decibel Warning")
                .setSmallIcon(R.drawable.ic_state_warning_24dp)
                .setChannelId("1")
                .setContentText("");

        NotificationCompat.Builder heartRateNotification = new NotificationCompat.Builder(this)
                .setContentTitle("High Heart Rate Warning")
                .setSmallIcon(R.drawable.ic_state_warning_24dp)
                .setChannelId("1")
                .setContentText("View the coping page to help lowering your heart rate.");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    Constants.DEFAULT_NOTIFICATION_CHANNEL_ID, Constants.DEFAULT_NOTIFICATION_CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        homeViewModel.setDeviceEventListener(
                () -> notificationManager.notify(0, decibelNotification.build()),
                () -> notificationManager.notify(1, heartRateNotification.build())
        );
        //[end] notification
    }
}