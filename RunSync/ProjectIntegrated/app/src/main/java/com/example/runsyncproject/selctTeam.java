package com.example.runsyncproject;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class selctTeam extends AppCompatActivity {
    Button firstTeam,secondTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selct_team);

        firstTeam = findViewById(R.id.firstTeam);
        secondTeam = findViewById(R.id.secondteam);

        SharedPreferences sh = getSharedPreferences("CricketDB", MODE_PRIVATE);
        String first = sh.getString("HostTeam", "");
        String second = sh.getString("VisitorTeam", "");

//        firstTeam.setText(first);
//        secondTeam.setText(second);

        firstTeam.setText("Batsmans");
        secondTeam.setText("Bowlers");

        firstTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(selctTeam.this,batsmans_list.class);
                startActivity(in);
            }
        });

        secondTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(selctTeam.this,bowlers_list.class);
                startActivity(in);
            }
        });

    }
}