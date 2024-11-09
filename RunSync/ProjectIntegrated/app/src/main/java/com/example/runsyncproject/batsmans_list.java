package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class batsmans_list extends AppCompatActivity {

    private Spinner batsmanSpinner;
    private BatsmanDatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batsmans_list);


        dbHandler = new BatsmanDatabaseHandler(this);


        batsmanSpinner = findViewById(R.id.batsmanSpinner);


        List<String> batsmanList = dbHandler.getAllUniqueBatsmanNames();

        batsmanList.add(0, "Select player");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, batsmanList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        batsmanSpinner.setAdapter(adapter);


        batsmanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (position != 0) {
                    String selectedBatsmanName = batsmanList.get(position);


                    Intent intent = new Intent(batsmans_list.this, BatsmanStatsPage.class);
                    intent.putExtra("selectedBatsmanName", selectedBatsmanName);
                    startActivity(intent);
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
}


