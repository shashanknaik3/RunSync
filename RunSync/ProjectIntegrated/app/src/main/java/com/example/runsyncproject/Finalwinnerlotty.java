package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Finalwinnerlotty extends AppCompatActivity {
TextView winner;
    private LottieAnimationView lottieView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalwinnerlotty);

        winner = findViewById(R.id.winner);
        Intent intent = getIntent();
        String receivedMessage = intent.getStringExtra("winner");

        winner.setText("Winning Team is "+receivedMessage);

        lottieView2 =  findViewById(R.id.lottieView2);

        lottieView2.playAnimation();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(Finalwinnerlotty.this,start_match_details.class);
                startActivity(in);
            }
        }, 5000);

    }
}