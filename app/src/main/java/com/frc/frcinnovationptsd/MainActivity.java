package com.frc.frcinnovationptsd;

import android.animation.FloatEvaluator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

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
                R.id.navigation_dashboard, R.id.navigation_home , R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.coping, null);
        Button button = findViewById(R.id.exit_coping_heart_rate);
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setHeight(1500);
        popupWindow.setWidth(800);
        button.setOnClickListener(i ->
        {
            TextView tv = (findViewById(R.id.text_home));
            tv.setText("reee");
            popupWindow.showAtLocation(view, Gravity.CENTER, 10, 10);
        });
        popupWindow.setContentView(view);
        // register view listeners
        Log.println(Log.ASSERT,"x DP checker: ",
                "check " + getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().density );
        Log.println(Log.ASSERT,"y DP checker: ",
                "check " + getResources().getDisplayMetrics().heightPixels / getResources().getDisplayMetrics().density );

        final View welcomeText = findViewById(R.id.text_home);
        BasicAnimator.AnimateMultipleParallel(
                BasicAnimator.GetAnimator(welcomeText, "alpha", 3000,
                        new FloatEvaluator(), null, 0, 1),
                BasicAnimator.GetAnimator(welcomeText, "translationY", 3000,
                        new FloatEvaluator(), new DecelerateInterpolator(), -250, 0)
        );
    }
}