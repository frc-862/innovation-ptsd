package com.frc.frcinnovationptsd;

import android.animation.FloatEvaluator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import com.frc.frcinnovationptsd.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private FirebaseCloud database;
    Map<String, String> testUser = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testUser.put("name", "bob");
        testUser.put("heart", "32");
        testUser.put("pressure", "90");
        database = new FirebaseCloud("bob");

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
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
/*
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.coping_heart_rate, null);
        Button button = findViewById(R.id.coping_home);
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setHeight(1200);
        popupWindow.setWidth(1000);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        button.setOnClickListener(i ->
        {
            popupWindow.showAsDropDown(view);
        });
        popupWindow.setContentView(view);*/

        final View welcomeText = findViewById(R.id.text_home);
        BasicAnimator.AnimateMultipleParallel(
                BasicAnimator.GetAnimator(welcomeText, "alpha", 3000,
                        new FloatEvaluator(), null, 0, 1),
                BasicAnimator.GetAnimator(welcomeText, "translationY", 3000,
                        new FloatEvaluator(), new DecelerateInterpolator(), -250, 0)
        );

        NotificationCompat.Builder heartRateNotification = new NotificationCompat.Builder(this)
                .setContentTitle("High Heart Rate Warning")
                .setSmallIcon(R.drawable.ic_state_warning_24dp)
                .setChannelId("1")
                .setContentText("View the coping page to help lowering your heart rate.");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    Constants.DEFAULT_NOTIFICATION_CHANNEL_ID, Constants.DEFAULT_NOTIFICATION_CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(1, heartRateNotification.build());
    }
}