package com.frc.frcinnovationptsd;

import android.animation.FloatEvaluator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        BasicAnimator.AnimateMultipleSequential(
                BasicAnimator.GetAnimator(
                        findViewById(R.id.testButton),
                        "alpha",
                        4000,
                        new FloatEvaluator(),
                        new LinearInterpolator(),
                        0, 1
                ),
                BasicAnimator.GetAnimator(
                        findViewById(R.id.testButton),
                        "alpha",
                        4000,
                        new FloatEvaluator(),
                        new LinearInterpolator(),
                        1, 0
                )
        );
        // register view listeners
    }
}