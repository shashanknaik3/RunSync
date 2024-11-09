package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectInnings extends AppCompatActivity {
    Button in1,in2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_innings);
        in1 = findViewById(R.id.firstinnings);
        in2 = findViewById(R.id.secondinninngs);

        in1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(selectInnings.this,graph.class);
                startActivity(in1);

            }
        });

        in2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(selectInnings.this, graphtwo.class);
                startActivity(in2);
            }
        });
    }
}