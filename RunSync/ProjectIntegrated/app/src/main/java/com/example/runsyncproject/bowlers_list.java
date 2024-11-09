package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class bowlers_list extends AppCompatActivity {

    private Spinner bowlerSpinner;
    private BolwerDataBaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowlers_list);


        dbHandler = new BolwerDataBaseHandler(this);


        bowlerSpinner = findViewById(R.id.bowlerSpinner);


        List<String> bowlerNames = dbHandler.getAllUniqueBowlerNames();
        bowlerNames.add(0, "Select player");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bowlerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        bowlerSpinner.setAdapter(adapter);


        bowlerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String selectedBowlerName = bowlerNames.get(i);


                    Intent intent = new Intent(bowlers_list.this, BatsmanStatsPage.class);
                    intent.putExtra("selectedBowlerName", selectedBowlerName);
                    startActivity(intent);
                } else {

                }
        }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}