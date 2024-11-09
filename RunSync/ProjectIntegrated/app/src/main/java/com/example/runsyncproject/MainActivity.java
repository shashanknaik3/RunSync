package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button guest,google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guest = findViewById(R.id.guest);
        google = findViewById(R.id.google);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Successfully loged in as Guest",Toast.LENGTH_SHORT).show();
                Intent match_details = new Intent(MainActivity.this,start_match_details.class);
                startActivity(match_details);

            }

        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Successfully logged in as Google User",Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this,start_match_details.class);
                startActivity(in);

            }
        });
    }
}