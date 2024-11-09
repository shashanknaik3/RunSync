package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SelectOpeningPlayers extends AppCompatActivity {
EditText Estriker,EnonStriker,Ebowler;
String striker,nonStriker,Sbowler;
Button startMatch;

TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_opening_players);
        Estriker = findViewById(R.id.striker);
        EnonStriker =findViewById(R.id.nonstriker);
        Ebowler = findViewById(R.id.bowler);


        startMatch = findViewById(R.id.start_match);
        test = findViewById(R.id.playtest);



        startMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                striker= Estriker.getText().toString();
                nonStriker = EnonStriker.getText().toString();
                Sbowler = Ebowler.getText().toString();

                SharedPreferences database = getSharedPreferences("CricketDB",MODE_PRIVATE);
                SharedPreferences.Editor  edit = database.edit();

                edit.putString("Striker",striker);
                edit.putString("NonStriker",nonStriker);
                edit.putString("Bowler",Sbowler);
                edit.putString("Innings","1");
                edit.apply();
//                Toast.makeText(getApplicationContext(),"Saved to Database Successfully",Toast.LENGTH_SHORT).show();


//                SharedPreferences sh = getSharedPreferences("CricketDB",MODE_PRIVATE);
//                String s1,s2,s3;
//                s1 = sh.getString("Striker","");
//                s2 = sh.getString("NonStriker","");
//                s3 = sh.getString("Bowler","");
//                String Ans = s1+" "+s2+" "+s3;
//                Toast.makeText(getApplicationContext(),Ans,Toast.LENGTH_SHORT).show();
//                test.setText(Ans);

                Intent start = new Intent(SelectOpeningPlayers.this,ScoreCard.class);
                startActivity(start);


            }
        });


    }
}