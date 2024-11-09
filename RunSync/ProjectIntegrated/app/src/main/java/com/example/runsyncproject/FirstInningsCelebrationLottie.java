package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class FirstInningsCelebrationLottie extends AppCompatActivity {

    private LottieAnimationView lottieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_innings_celebration_lottie);

        lottieView = findViewById(R.id.lottieView);

        lottieView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();
            }
        }, 5000);
    }
}
