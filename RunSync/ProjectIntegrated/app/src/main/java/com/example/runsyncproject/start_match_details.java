package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class start_match_details extends AppCompatActivity {
    String TossWonBy,OptedTo;
    int Overs;
    String HostTeamName,VisitorTeamName;
    Button start;
    EditText hostname,visitorname,overs;
    RadioGroup rdtoss;
    RadioGroup rdopted;
    RadioButton tossHost,tossvisitor,bat,bowl;
    ImageButton plist,graph;
    Button toss;


    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_match_details);
        hostname = findViewById(R.id.hostname);
        visitorname = findViewById(R.id.vistorname);
        toss = findViewById(R.id.toss);
        overs = findViewById(R.id.overs);
        hostname = findViewById(R.id.hostname);
        tossHost = findViewById(R.id.Toss_host);
        tossvisitor = findViewById(R.id.Toss_visitor);
        bat = findViewById(R.id.bat);
        bowl = findViewById(R.id.bowl);

        graph = findViewById(R.id.graph);

        start = findViewById(R.id.start);
        rdtoss = findViewById(R.id.hostGroup);
        rdopted = findViewById(R.id.optedGroup);

        plist = findViewById(R.id.playerslist);

      //  t1 = findViewById(R.id.ans);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(!rdtoss.isSelected()||!rdopted.isSelected()){
//                    Toast.makeText(getApplicationContext(),"All fields are mandatory",Toast.LENGTH_SHORT).show();
//                }

                HostTeamName = hostname.getText().toString();
                VisitorTeamName = visitorname.getText().toString();
                Overs = Integer.parseInt(overs.getText().toString());
                String tossWWW ;

                if(bat.isChecked()){

                    if(tossHost.isChecked()){
                        tossWWW = hostname.getText().toString();
                    }else{
                        tossWWW = visitorname.getText().toString();
                    }
                }else{
                    if(tossHost.isChecked()){
                        tossWWW = visitorname.getText().toString();
                    }else{
                        tossWWW = hostname.getText().toString();
                    }
                }


//                Toast.makeText(getApplicationContext(),"Host Team :" + HostTeamName,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"Visitor Team :" + VisitorTeamName,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"Toss Won by :" + TossWonBy + " Opted to "+OptedTo,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"Overs:" + Overs,Toast.LENGTH_SHORT).show();


                SharedPreferences database = getSharedPreferences("CricketDB",MODE_PRIVATE);
                SharedPreferences.Editor  edit = database.edit();


                edit.putString("HostTeam",HostTeamName);
                edit.putString("VisitorTeam",VisitorTeamName);
                edit.putString("TossWonBy",TossWonBy);
                edit.putString("OptedTo",OptedTo);
                edit.putString("Innings","1");
                edit.putString("FirstBattingTeam",tossWWW);
                edit.putString("Overs",(String.valueOf(Overs)));
                edit.commit();
                Toast.makeText(getApplicationContext(),"Saved to Database Successfully",Toast.LENGTH_SHORT).show();

//                SharedPreferences sh = getSharedPreferences("CricketDB",MODE_PRIVATE);
//                String s1,s2,s3,s4,s5;
//                s1 = sh.getString("HostTeam","");
//                s2 = sh.getString("VisitorTeam","");
//                s3 = sh.getString("TossWonBy","");
//                s4 =  sh.getString("OptedTo","");
//                s5 = sh.getString("Overs","");
//                String Ans = s1+" "+s2+" "+s3+" "+s4+" "+s5;
//                t1.setText(Ans);


                Intent  start = new Intent(start_match_details.this,SelectOpeningPlayers.class);
                startActivity(start);

            }
        });

        rdtoss.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int toss_host = R.id.Toss_host;
                int toss_visitor = R.id.Toss_visitor;
                if(i==toss_host)
                    TossWonBy = hostname.getText().toString();
                else if(i==toss_visitor)
                    TossWonBy = visitorname.getText().toString();

            }
        });

        rdopted.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int toss_bat = R.id.bat;
                int toss_bowl = R.id.bowl;
                if(i==toss_bat)
                    OptedTo = "Bat";
                else if(i==toss_bowl)
                    OptedTo = "Bowl";
            }
        });


        plist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(start_match_details.this,selctTeam.class);
                startActivity(in);


            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn = new Intent(start_match_details.this,selectInnings.class);
                startActivity(inn);
            }
        });

        toss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tossHost.setText(hostname.getText().toString());
                tossvisitor.setText(visitorname.getText().toString());
                toss.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

    }

}