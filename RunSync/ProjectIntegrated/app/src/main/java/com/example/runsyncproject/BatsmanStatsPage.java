package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//public class BatsmanStatsPage  extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_batsman_stats_page);
//
//        String selectedBatsmanName = getIntent().getStringExtra("selectedBatsmanName");
//
//
//        Individual_Batsman_Page playerStatsFragment = new Individual_Batsman_Page();
//        Bundle args = new Bundle();
//        args.putString("batsmanName", selectedBatsmanName);
//        playerStatsFragment.setArguments(args);
//
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.playerStatsFragmentContainer, playerStatsFragment)
//                .commit();
//    }
//
//}

public class BatsmanStatsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batsman_stats_page);


        String selectedBowlerName = getIntent().getStringExtra("selectedBowlerName");
        String selectedBatsmanName = getIntent().getStringExtra("selectedBatsmanName");

        if (selectedBatsmanName != null) {

            Individual_Batsman_Page batsmanStatsFragment = new Individual_Batsman_Page();
            Bundle args = new Bundle();
            args.putString("batsmanName", selectedBatsmanName);
            batsmanStatsFragment.setArguments(args);


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.playerStatsFragmentContainer, batsmanStatsFragment)
                    .commit();
        } else {

            Individual_Bowler_Page_fragment bowlerStatsFragment = new  Individual_Bowler_Page_fragment();
            Bundle args = new Bundle();
            args.putString("bowlerName", selectedBowlerName);
            bowlerStatsFragment.setArguments(args);


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.playerStatsFragmentContainer, bowlerStatsFragment)
                    .commit();
        }
    }
}

