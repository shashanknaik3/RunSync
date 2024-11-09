
package com.example.runsyncproject;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ScoreCard extends AppCompatActivity {
    TextView matchHeading, txtInnings, overdisp;
    Batsman p1, p2;
    Bowler b1;
    TextView four1, four2;
    TextView star1, star2;
    TextView six1, six2;
    Button wicket;
    TextView ball1, ball2;
    TextView firstbatsman, secondbatsman,Target;
    TextView targetLabel;
    TextView rrr;
    TextView rrrlabel;

    String striker, nonstriker;
    String bolwername;
    int innings;

    int total_run = 0, wickets = 0;
    int overs;
    int balls;
    Button zero, one, two, three, four, five, six;
    String firstTeam, secondTeam;
    Button extraBTN;
    TextView txtScore;
    int target;

    Button partnership;
    TextView run1, run2;
    String out_player;


    TextView bowler;
    TextView b_Over,b_maiden,b_run,b_wicket;
    TextView runRate;
    TextView sr1,sr2;
    Bowler existingBowler,newBowler;
    TextView  Economy;
    TextView thisover;
    int matchOvers;
    int extra;
    CheckBox wide;
    CheckBox noball;
    CheckBox byes;

    String FirstbattingTeamName,SecondBattingTeamName;

    private EditText strikerEditText;
    private EditText nonStrikerEditText;
    private EditText openingBowlerEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);

        matchHeading = findViewById(R.id.matchheading);
        txtInnings = findViewById(R.id.txtInnings);
        txtScore = findViewById(R.id.txtScore);
        zero = findViewById(R.id.btn0);
        one = findViewById(R.id.btn1);
        two = findViewById(R.id.btn2);
        three = findViewById(R.id.btn3);
        four = findViewById(R.id.btn4);
        five = findViewById(R.id.btn5);
        six = findViewById(R.id.btn6);
        overdisp = findViewById(R.id.overdisp);
        firstbatsman = findViewById(R.id.firstbatsman);
        secondbatsman = findViewById(R.id.secondbatsman);
        run1 = findViewById(R.id.run1);
        run2 = findViewById(R.id.run2);
        four1 = findViewById(R.id.four1);
        four2 = findViewById(R.id.four2);
        six1 = findViewById(R.id.six1);
        six2 = findViewById(R.id.six2);
        ball1 = findViewById(R.id.ball1);
        ball2 = findViewById(R.id.ball2);
        wicket = findViewById(R.id.wicket);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);

        //bowler
        bowler = findViewById(R.id.bowlername);
        b_Over = findViewById(R.id.txtBOver);
        b_maiden = findViewById(R.id.txtBMaiden);
        b_wicket = findViewById(R.id.txtBwicket);
        b_run = findViewById(R.id.txtBRuns);
        Target = findViewById(R.id.txtTarget);

        runRate = findViewById(R.id.txtCRR);

        targetLabel =  findViewById(R.id.Target);
        rrr = findViewById(R.id.rrr);
        rrrlabel = findViewById(R.id.rrrLabel);
        partnership = findViewById(R.id.btnPartnership);
        sr1 = findViewById(R.id.txtSR1);
        sr2 = findViewById(R.id.txtSR2);
        Economy = findViewById(R.id.Ec_Rate);
        thisover = findViewById(R.id.thisOver);
        wide = findViewById(R.id.chkWide);
        noball = findViewById(R.id.chkNoBall);
        byes = findViewById(R.id.chkByes);
        extraBTN = findViewById(R.id.btnExtras);



        SharedPreferences sh = getSharedPreferences("CricketDB", MODE_PRIVATE);

        firstTeam = sh.getString("HostTeam", "");
        secondTeam = sh.getString("VisitorTeam", "");
        FirstbattingTeamName = sh.getString("FirstBattingTeam","");
        innings=Integer.parseInt(sh.getString("Innings",""));


        matchOvers = Integer.parseInt(sh.getString("Overs",""));

        if(FirstbattingTeamName.equals(firstTeam))
        {
            SecondBattingTeamName = secondTeam;
        }
        else{
            SecondBattingTeamName = firstTeam;
        }

        matchHeading.setText(firstTeam + " VS " + secondTeam);
        if(innings==2) {
            txtInnings.setText("2nd Innings"+"("+SecondBattingTeamName+")");
            target = Integer.parseInt(sh.getString("Target",""));
           target=target+1;
            Target.setText((String.valueOf(target)+"("+matchOvers+".0"+")"));

        }
        else{
            rrr.setText("");
            rrrlabel.setText("");
            targetLabel.setText("");
            txtInnings.setText("1st Innings"+ "("+FirstbattingTeamName+")");
        }
        striker = sh.getString("Striker", "");
        nonstriker = sh.getString("NonStriker", "");
        bolwername = sh.getString("Bowler","");
        Toast.makeText(getApplicationContext(),bolwername, LENGTH_SHORT).show();

        p1 = new Batsman(striker);
        p2 = new Batsman(nonstriker);
        b1 = new Bowler(bolwername);


    printStar();
    InitialStart();
    printScore();
    printCRR();

    extraBTN.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Extras:"+String.valueOf(extra),LENGTH_SHORT).show();
        }
    });

    zero.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(wide.isChecked()){
                b1.setRuns(1);
                calcScore("wide",0);//10 for wide
                printScore();
                printStar();
                printCRR();
                checkMatchOver();
            }else if(noball.isChecked()) {
                b1.setRuns(1);
                calcScore("noball",0);
                printScore();
                printStar();
                printCRR();
                checkMatchOver();
            }
            else{
                if (striker.equals(p1.name)) {
                    p1.addballs();
                } else {
                    p2.addballs();
                }
                calcovers();
                calcScore("genuine",0);
                printScore();
                printStar();
                printCRR();
                checkMatchOver();
            }
            wide.setChecked(false);
            noball.setChecked(false);
            byes.setChecked(false);
        }
    });

    one.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (wide.isChecked()) {
                b1.setRuns(1);
                calcScore("wide", 1);
                swaplayers();
                printScore();
                printStar();
                printCRR();
                checkMatchOver();
            } else if (noball.isChecked()) {
                if (striker.equals(p1.name)) {
                    p1.addruns(1);
                } else {
                    p2.addruns(1);
                }
                b1.setRuns(1);
                calcScore("noball", 1);
                swaplayers();
                printScore();
                printStar();
                printCRR();
                checkMatchOver();
            }
            else if (byes.isChecked()) {
                if (striker.equals(p1.name)) {
                    p1.addballs();
                } else {
                    p2.addballs();
                }
                calcovers();
                calcScore("bye", 1);
                swaplayers();
                printScore();
                printStar();
                printCRR();
                checkMatchOver();
            }
            else {
                if (striker.equals(p1.name)) {
                    p1.addruns(1);
                    p1.addballs();
                } else {
                    p2.addruns(1);
                    p2.addballs();
                }
                b1.setRuns(1);
                calcovers();
                calcScore("genuine", 1);
                swaplayers();
                printScore();
                printStar();
                printCRR();
                checkMatchOver();
            }
            wide.setChecked(false);
            noball.setChecked(false);
            byes.setChecked(false);
        }
    });


two.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if(wide.isChecked()){
            b1.setRuns(2);
            calcScore("wide",2);
            printScore();
            printStar();
            printCRR();
            checkMatchOver();
        }
        else if(noball.isChecked()){
            if (striker.equals(p1.name)) {
                p1.addruns(2);
            } else {
                p2.addruns(2);
            }
            b1.setRuns(2);
            calcScore("noball",2);
            printScore();
            printStar();
            printCRR();
            checkMatchOver();
        }
        else if(byes.isChecked()){
            if (striker.equals(p1.name)) {
                p1.addballs();
            } else {
                p2.addballs();
            }
            calcovers();
            calcScore("byes",2);
            printScore();
            printStar();
            printCRR();
            checkMatchOver();
        }
        else {
            if (striker.equals(p1.name)) {
                p1.addruns(2);
                p1.addballs();
            } else {
                p2.addruns(2);
                p2.addballs();
            }

            b1.setRuns(2);
            calcovers();
            calcScore("genuine", 2);
            printScore();
            printStar();
            printCRR();
            checkMatchOver();
        }
        wide.setChecked(false);
        noball.setChecked(false);
        byes.setChecked(false);

}
        });

            three.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if(wide.isChecked()){
                        b1.setRuns(3);
                        calcScore("wide",3);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(noball.isChecked()){
                        if (striker.equals(p1.name)) {
                            p1.addruns(3);
                        } else {
                            p2.addruns(3);
                        }
                        b1.setRuns(3);
                        calcScore("noball",3);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(byes.isChecked()){
                        if (striker.equals(p1.name)) {
                            p1.addballs();
                        } else {
                            p2.addballs();
                        }
                        calcovers();//save to db if balls = 6
                        calcScore("byes",3);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }else {
                        if (striker.equals(p1.name)) {
                            p1.addruns(3);
                            p1.addballs();
                        } else {
                            p2.addruns(3);
                            p2.addballs();
                        }

                        b1.setRuns(3);
                        calcovers();//save to db if balls = 6
                        calcScore("genuine", 3);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();

                    }
                    wide.setChecked(false);
                    noball.setChecked(false);
                    byes.setChecked(false);
                }
            });

            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(wide.isChecked()){
                        b1.setRuns(4);
                        calcScore("wide",4);
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(noball.isChecked()){
                        if (striker.equals(p1.name)) {
                            p1.addruns(4);
                        } else {
                            p2.addruns(4);
                        }
                        b1.setRuns(4);
                        calcScore("noball",4);
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(byes.isChecked()){
                        calcovers();
                        calcScore("byes",4);
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }

                    else {
                        if (striker.equals(p1.name)) {
                            p1.addruns(4);
                            p1.addballs();
                        } else {
                            p2.addruns(4);
                            p2.addballs();
                        }


                        b1.setRuns(4);
                        calcovers();
                        calcScore("genuine", 4);
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    wide.setChecked(false);
                    noball.setChecked(false);
                    byes.setChecked(false);
            }
            });

            five.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(wide.isChecked()) {
                        b1.setRuns(5);
                        calcScore("wide",5);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(noball.isChecked()){
                        if (striker.equals(p1.name)) {
                            p1.addruns(5);
                        } else {
                            p2.addruns(5);
                        }
                        b1.setRuns(5);
                        calcScore("noball",5);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(byes.isChecked()){
                        if (striker.equals(p1.name)) {
                            p1.addballs();
                        } else {
                            p2.addballs();
                        }
                        calcovers();
                        calcScore("byes",5);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    else {
                        if (striker.equals(p1.name)) {
                            p1.addruns(5);
                            p1.addballs();
                        } else {
                            p2.addruns(5);
                            p2.addballs();
                        }


                        b1.setRuns(5);
                        calcovers();
                        calcScore("genuine", 5);
                        swaplayers();
                        printScore();
                        printStar();
                        printCRR();
                        checkMatchOver();
                    }
                    wide.setChecked(false);
                    noball.setChecked(false);
                    byes.setChecked(false);
                }
            });

            six.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(wide.isChecked()){
                        b1.setRuns(6);
                        calcScore("wide",6);
                        printStar();
                        printScore();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(noball.isChecked()){
                        if (striker.equals(p1.name)) {
                            p1.addruns(6);
                        } else {
                            p2.addruns(6);
                        }
                        b1.setRuns(6);
                        calcScore("noball",6);
                        printStar();
                        printScore();
                        printCRR();
                        checkMatchOver();
                    }
                    else if(byes.isChecked()){
                        calcovers();
                        calcScore("byes",6);
                        printStar();
                        printScore();
                        printCRR();
                        checkMatchOver();
                    }else {
                        if (striker.equals(p1.name)) {
                            p1.addruns(6);
                            p1.addballs();
                        } else {
                            p2.addruns(6);
                            p2.addballs();
                        }


                        b1.setRuns(6);
                        calcovers();
                        calcScore("genuine", 6);
                        printStar();
                        printScore();
                        printCRR();
                        checkMatchOver();

                    }
                    wide.setChecked(false);
                    noball.setChecked(false);
                    byes.setChecked(false);
                }
            });



        wicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++wickets;
                b1.setWickets();

//                if (checkMatchOver() != 1) {

                    if (striker.equals(p1.name)) {
                        out_player = p1.name;
                        p1.addballs();
                        //saveToDB(p1);
                        Toast.makeText(getApplicationContext(), p1.name + " Got Out", LENGTH_SHORT).show();
                    } else {
                        out_player = p2.name;
                        p2.addballs();
                       // saveToDB(p2);
                        Toast.makeText(getApplicationContext(), p2.name + " Got Out", LENGTH_SHORT).show();
                    }

                calcovers();
                    calcScore("wicket",0);
                printScore();
                printCRR();

                if (checkMatchOver() == 1){

                }else {
                    saveToDB(p1);
                    saveToDB(p2);
                    showBatsmanNameDialog();
                }
            }
        });

        partnership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),(p1.getRuns()+p2.getRuns())+" Runs Out Of "+(p1.getBalls()+p2.getBalls())+" Balls", LENGTH_SHORT).show();
            }
        });
    }

    private void printCRR() {
        double ors = overs+(balls*0.1666);
        double crr = total_run/ors;
        String formattedCRR = String.format("%.1f", crr);
        if(formattedCRR.equals("NaN")){
            formattedCRR="0.0";
        }
        if(formattedCRR.equals("Infinity")){
            formattedCRR="36+";
        }
        runRate.setText(formattedCRR);


        if(innings==2) {
            if (total_run < target) {
                float tot_balls = matchOvers * 6;
                int cur_balls = (overs * 6) + balls;
                int req_runs = target - total_run;
//                Toast.makeText(getApplicationContext(), String.valueOf(target), LENGTH_SHORT).show();
                double rr = (req_runs / (tot_balls - cur_balls)) * 6;
                if(rr>36) {
                    rrr.setText("36+");
                }
                else{
                    String formattedRRR = String.format("%.1f", rr);
                    rrr.setText(formattedRRR);
                }
            }
        }

    }

//    private void start() {
//        SharedPreferences sh = getSharedPreferences("CricketDB", MODE_PRIVATE);
//
//        firstTeam = sh.getString("HostTeam", "");
//        secondTeam = sh.getString("VisitorTeam", "");
//        innings=Integer.parseInt(sh.getString("Innings",""));
//
//        matchOvers = Integer.parseInt(sh.getString("Overs",""));
//
//        matchHeading.setText(firstTeam + " VS " + secondTeam);
//        if(innings==2) {
//            txtInnings.setText("2nd Innings");
//        }
//        else{
//            txtInnings.setText("1st Innings");
//        }
//        striker = sh.getString("Striker", "");
//        nonstriker = sh.getString("NonStriker", "");
//        bolwername = sh.getString("Bowler","");
//        Toast.makeText(getApplicationContext(),bolwername, LENGTH_SHORT).show();
//
//        p1 = new Batsman(striker);
//        p2 = new Batsman(nonstriker);
//
//        b1 = new Bowler(bolwername);
//    }

    private int checkMatchOver() {
        if(innings==2)
        {
            if(total_run>=target)
            {
                saveToDB(p1);
                saveToDB(p2);
                saveToDB(b1);
                if(balls!=0){
                    saveEachOverRun(b1);
                }

                p1=null;
                p2=null;
                b1=null;
                Intent intent = new Intent(ScoreCard.this,Finalwinnerlotty.class);
                String message = SecondBattingTeamName;
                intent.putExtra("winner", message);
                startActivity(intent);

                //showWinningDialog(2);
                return 1;

            }
            if(wickets==10 || overs==matchOvers)
            {
                if(wickets==10 && total_run<target-1)
                {
                    saveToDB(p1);
                    saveToDB(p2);
                    saveToDB(b1);
                    saveEachOverRun(b1);
                    //showWinningDialog(1);
                    p1=null;
                    p2=null;
                    b1=null;

                    Intent intent = new Intent(ScoreCard.this,Finalwinnerlotty.class);
                    String message = FirstbattingTeamName;
                    intent.putExtra("winner", message);
                    startActivity(intent);

                    return 1;

                }
                if(total_run<target-1)
                {
                    saveToDB(p1);
                    saveToDB(p2);
                    saveToDB(b1);

                   // showWinningDialog(1);
                    p1=null;
                    p2=null;
                    b1=null;

                    Intent intent = new Intent(ScoreCard.this,Finalwinnerlotty.class);
                    String message = FirstbattingTeamName;
                    intent.putExtra("winner", message);
                    startActivity(intent);

                    return 1;
                }

                if(total_run==target-1 )
                {
                    saveToDB(p1);
                    saveToDB(p2);
                    saveToDB(b1);

                    p1=null;
                    p2=null;
                    b1=null;
                   // showWinningDialog(0);

                    Intent intent = new Intent(ScoreCard.this,Finalwinnerlotty.class);
                    String message = "Match Tied";
                    intent.putExtra("winner", message);
                    startActivity(intent);
                    return 1;
                }

                if(total_run>=target)
                {
                    saveToDB(p1);
                    saveToDB(p2);
                    saveToDB(b1);

                    p1=null;
                    p2=null;
                    b1=null;
                    //showWinningDialog(2);

                    Intent intent = new Intent(ScoreCard.this,Finalwinnerlotty.class);
                    String message = SecondBattingTeamName;
                    intent.putExtra("winner", message);
                    startActivity(intent);
                    return 1;
                }
            }
        }

        if(innings == 1 && overs==matchOvers || wickets==10)
        {
            saveToDB(p1);
            saveToDB(p2);

            if(balls!=0 || overs==matchOvers) {
                saveToDB(b1);
            }

            Intent in = new Intent(ScoreCard.this,FirstInningsCelebrationLottie.class);
            startActivity(in);
            showInningsOverDialog(this);
            return  0;

        }
        return 0;
    }

    private void showWinningDialog(int i) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Match Result");


            String winningTeamName = "";
            if (i == 1) {
                winningTeamName = FirstbattingTeamName;
            } else if (i == 2) {
                rrr.setText("0.0");
                winningTeamName = SecondBattingTeamName;
            } else if(i==0){
                winningTeamName = "Match Tied";
            }

            builder.setMessage("The winning team is: " + winningTeamName);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    dialog.dismiss();
                    Intent in = new Intent(ScoreCard.this,start_match_details.class);
                    startActivity(in);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }


//    public void showInningsOverDialog(Context context) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Innings Over");
//        builder.setMessage("The first innings is over.");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//
//
//                AlertDialog.Builder secondBuilder = new AlertDialog.Builder(context);
//                secondBuilder.setTitle("Second Innings Initialization");
//                LayoutInflater inflater = getLayoutInflater();
//                View dialogView = inflater.inflate(R.layout.second_innings_initilisation, null);
//                secondBuilder.setView(dialogView);
//
//
////                final EditText strikerEditText = dialogView.findViewById(R.id.editStriker);
////                final EditText nonStrikerEditText = dialogView.findViewById(R.id.editNonStriker);
////                final EditText openingBowlerEditText = dialogView.findViewById(R.id.editOpeningBowler);
//
//                secondBuilder.setPositiveButton("Save and Initialize", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        String strikerName = strikerEditText.getText().toString();
//                        String nonStrikerName = nonStrikerEditText.getText().toString();
//                        String openingBowlerName = openingBowlerEditText.getText().toString();
//
//                        SharedPreferences database = getSharedPreferences("CricketDB", MODE_PRIVATE);
//                        SharedPreferences.Editor edit = database.edit();
//
//                        edit.putString("Striker", strikerName);
//                        edit.putString("NonStriker", nonStrikerName);
//                        edit.putString("Bowler", openingBowlerName);
//                        edit.putString("Innings", "2");
//                        edit.apply();
//
//                        Toast.makeText(getApplicationContext(), "2nd innings players initialized successfully", Toast.LENGTH_SHORT).show();
//
//                        Intent in = new Intent(ScoreCard.this, ScoreCard.class);
//                        startActivity(in);
//
//                        dialog.dismiss();
//                    }
//                });
//
//
//                AlertDialog secondDialog = secondBuilder.create();
//                secondDialog.show();
//            }
//        });
//
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    public void  showInningsOverDialog(Context context) {



        Log.d("SecondInnings","Second innings First Line");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Second Innings Initialization");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.second_innings_initilisation, null);
        builder.setView(dialogView);

        strikerEditText = dialogView.findViewById(R.id.editStriker);
        nonStrikerEditText = dialogView.findViewById(R.id.editNonStriker);
        openingBowlerEditText = dialogView.findViewById(R.id.editOpeningBowler);
        Log.d("SecondInnings","Second innings before OK Line");

        builder.setPositiveButton("Save and Initialize", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                String strikerName = strikerEditText.getText().toString();
                Log.d("SecondInnings","After Striker "+strikerName);
                String nonStrikerName = nonStrikerEditText.getText().toString();
                Log.d("SecondInnings","After Non Striker "+nonStrikerName);
                String openingBowlerName = openingBowlerEditText.getText().toString();
                Log.d("SecondInnings","After Bowler "+openingBowlerName);

                SharedPreferences database = getSharedPreferences("CricketDB", MODE_PRIVATE);
                SharedPreferences.Editor edit = database.edit();
                Log.d("SecondInnings","After editable database");

                edit.putString("Striker", strikerName);
                edit.putString("NonStriker", nonStrikerName);
                edit.putString("Bowler", openingBowlerName);
                edit.putString("Innings", "2");
                edit.putString("Target",String.valueOf(total_run));
                edit.apply();
                Log.d("SecondInnings","After apply");

//                Toast.makeText(getApplicationContext(), "2nd innings players initialized successfully", Toast.LENGTH_SHORT).show();
                Log.d("SecondInnings","After toast");

                Intent in = new Intent(ScoreCard.this, ScoreCard.class);
                Log.d("SecondInnings","After creating intent");
                startActivity(in);
                Log.d("SecondInnings","After starting intent");

                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    private void saveToDB(Batsman obj) {
        if(obj!=null) {
            BatsmanDatabaseHandler dbHandler = new BatsmanDatabaseHandler(this);
            Batsman batsman = new Batsman(obj.name);
            batsman.setRuns(obj.runs);
            batsman.setBalls(obj.getBalls());
            batsman.setFour(obj.getFour());
            batsman.setSix(obj.getSix());
            dbHandler.addBatsman(batsman);
        }

    }

//    private void saveToDB(Bowler obj){
//        BolwerDataBaseHandler dbhandler = new BolwerDataBaseHandler(this);
//        Bowler bowler = new Bowler(obj.name);
//        bowler.setBalls(obj.getBalls());
//        bowler.setMaidens(obj.getMaidens());
//        bowler.setOvers(obj.getOvers());
//        bowler.setRuns2(obj.getRuns());
//        bowler.setWickets(obj.getWickets());
//        dbhandler.addBolwer(bowler);
//
//    }

    private void saveToDB(Bowler obj) {
        if(obj!=null) {
            BolwerDataBaseHandler dbhandler = new BolwerDataBaseHandler(this);

            String currentBowlerName = obj.getName();
            existingBowler = dbhandler.getBowlerData(currentBowlerName);


            if (existingBowler != null) {
                // Toast.makeText(getApplicationContext(),existingBowler.getRuns()+" "+existingBowler.getBalls()+" "+existingBowler.getOvers(), LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(),obj.getRuns()+" "+obj.getBalls()+" "+obj.getOvers(), LENGTH_SHORT).show();

                // If the bowler exists, update their statistics
                existingBowler.setBalls(existingBowler.getBalls() + obj.getBalls());
                existingBowler.setMaidens(existingBowler.getMaidens() + obj.getMaidens());
                existingBowler.setOvers(existingBowler.getOvers() + obj.getOvers());
                existingBowler.setRuns2(existingBowler.getRuns() + obj.getRuns());
                existingBowler.setWickets(existingBowler.getWickets() + obj.getWickets());

                dbhandler.updateBowler(existingBowler);
            } else {

                newBowler = new Bowler(currentBowlerName, obj.getOvers(), obj.getMaidens(), obj.getRuns(), obj.getWickets());
                newBowler.setBalls(obj.getBalls());

                dbhandler.addBolwer(newBowler);
            }
        }
    }



    private void showBatsmanNameDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.batsman_name_dialog);


        EditText editTextBatsmanName = dialog.findViewById(R.id.editTextBatsmanName);
        Button buttonOK = dialog.findViewById(R.id.buttonOK);
        Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newBatsmanName = editTextBatsmanName.getText().toString();


                if (!newBatsmanName.isEmpty()) {
                    SharedPreferences database = getSharedPreferences("CricketDB", MODE_PRIVATE);
                    SharedPreferences.Editor edit = database.edit();
                    edit.putString("newBatsman", newBatsmanName);
                    edit.commit();
//                    Toast.makeText(getApplicationContext(), "New Batsman Added to shared memory", LENGTH_SHORT).show();

                    dialog.dismiss();


                    if (out_player.equals(p1.name)) {
                        p1 = new Batsman(newBatsmanName);
                        nonstriker = p2.name;

                    } else {
                        p2 = new Batsman(newBatsmanName);
                        nonstriker = p1.name;


                    }
                    striker = newBatsmanName;
                    firstbatsman.setText(p1.name);
                    secondbatsman.setText(p2.name);
                    printScore();
                    txtScore.setText(total_run + "-" + wickets);
                    printStar();


                    SharedPreferences prefs = getSharedPreferences("CricketDB", MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putString("Striker", newBatsmanName);
                    prefsEditor.putString("NonStriker",nonstriker);
                    prefsEditor.commit();

                } else {
                    Toast.makeText(ScoreCard.this, "Please enter a valid batsman's name", LENGTH_SHORT).show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        afterWicketInitilisation();
    }

    private void swaplayers() {
        String temp=striker;
        striker = nonstriker;
        nonstriker = temp;


    }

    private void calcScore(String type,int r) {
            if(type.equals("wide"))
            {
            total_run = total_run + r+1;
            extra = extra+r+1;
            thisover.setText((thisover.getText().toString())+"  " +r+ "WD");
             }

        else if(type.equals("noball")) {
                total_run = total_run + r+1;
                extra = extra+1;
            thisover.setText((thisover.getText().toString())+"  " + r + "NB");
        }
        else if(type.equals("byes")){
            total_run = total_run + r;
                extra = extra+r;
            if (balls == 0) {
                thisover.setText("");
            } else {
                thisover.setText((thisover.getText().toString()) +"  "+ r+"B");
            }
        }

        else if (type.equals("genuine")) {
                total_run = total_run + r;
                if (balls == 0) {
                    thisover.setText("");
                } else {
                    thisover.setText((thisover.getText().toString()) +"  "+ r);
                }
            }
        else if(type.equals("wicket")){
                if (balls == 0) {
                    thisover.setText("");
                } else {
                    thisover.setText((thisover.getText().toString()) +"  "+ "W");
                }
            }
    }
    private void calcovers() {

        b1.setBalls();
        if(balls<6)
        {
            ++balls;
        }
        if(balls==6 & overs!=matchOvers)
        {
            overs = overs+1;
            balls=0;
            swaplayers();
            saveEachOverRun(b1);//save run to run db for graph
            if(overs!=matchOvers) {
                saveToDB(b1);//save bolwer to db
            }
            if(overs!=matchOvers) {
                showBowlerNameDialog();
            }
        }

    }

    private void saveEachOverRun(Bowler obj) {
        RunsDatabaseHandler runsDatabaseHandler = new RunsDatabaseHandler(this);

        SharedPreferences sh = getSharedPreferences("CricketDB", MODE_PRIVATE);
        int innings = Integer.parseInt(sh.getString("Innings", ""));
        if (balls != 0) {
            runsDatabaseHandler.addRuns(obj.getRuns(), overs + 1, innings);
        } else {
            runsDatabaseHandler.addRuns(obj.getRuns(), overs, innings);
        }
    }

    private void printScore() {
        if(b1!=null) {

            txtScore.setText(String.valueOf(total_run) + "-" + String.valueOf(wickets));
            overdisp.setText("( " + String.valueOf(overs) + "." + String.valueOf(balls) + " )");

            ball1.setText(String.valueOf(p1.balls));
            ball2.setText(String.valueOf(p2.balls));
            run1.setText(String.valueOf(p1.runs));
            run2.setText(String.valueOf(p2.runs));
            four1.setText(String.valueOf(p1.four));
            four2.setText(String.valueOf(p2.four));
            six1.setText(String.valueOf(p1.six));
            six2.setText(String.valueOf(p2.six));

            float runs = p1.getRuns();
            float balls = p1.getBalls();
            String srate1 = String.format("%.1f", (runs / balls) * 100);
            if(srate1.equals("NaN")){
                srate1="0";
            }
            sr1.setText(srate1);
            float runs2 = p2.getRuns();
            float balls2 = p2.getBalls();
            String srate2 = String.format("%.1f", (runs2 / balls2) * 100);
            if(srate2.equals("NaN")){
                srate2="0";
            }
            sr2.setText(srate2);


            if (existingBowler != null && b1.getName().toString().equals(existingBowler.getName().toString())) {
                bowler.setText(b1.getName().toString());
                b_Over.setText(String.valueOf(existingBowler.getOvers() + "." + b1.getBalls()));
                b_maiden.setText(String.valueOf(existingBowler.getMaidens()));
                b_run.setText(String.valueOf(existingBowler.getRuns() + b1.getRuns()));
                b_wicket.setText(String.valueOf(existingBowler.getWickets() + b1.getWickets()));

                float ec_balls = (existingBowler.getOvers() * 6) + b1.getBalls();
                double ec_rate = ((existingBowler.getRuns() + b1.getRuns()) / ec_balls) * 6;
                String economyrate = String.format("%.1f", ec_rate);
                if(economyrate.equals("NaN")){
                    economyrate="0.0";
                }
                Economy.setText(economyrate);
            } else {
                bowler.setText(b1.getName().toString());
                b_Over.setText(String.valueOf(b1.getOvers() + "." + b1.getBalls()));
                b_maiden.setText(String.valueOf(b1.getMaidens()));
                b_run.setText(String.valueOf(b1.getRuns()));
                b_wicket.setText(String.valueOf(b1.getWickets()));

                float ec_balls = (b1.getOvers() * 6) + b1.getBalls();
                double ec_rate = (+b1.getRuns() / ec_balls) * 6;
                String economyrate = String.format("%.1f", ec_rate);
                if(economyrate.equals("NaN")){
                    economyrate="0.0";
                }
                Economy.setText(economyrate);
            }
        }

    }

    private void showBowlerNameDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_bowler_name);


        ListView listViewBowler = dialog.findViewById(R.id.listViewBowler);
        EditText editTextBowlerName = dialog.findViewById(R.id.editTextBowlerName);
        Button buttonOK = dialog.findViewById(R.id.buttonOK);
        Button buttonCancel = dialog.findViewById(R.id.buttonCancel);


        BolwerDataBaseHandler databaseHandler = new BolwerDataBaseHandler(this);
        List<String> uniqueBowlerNames = databaseHandler.getAllUniqueBowlerNames();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, uniqueBowlerNames);

        listViewBowler.setAdapter(adapter);

        listViewBowler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedBowlerName = adapter.getItem(position);
                editTextBowlerName.setText(selectedBowlerName);
            }
        });


        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newBowlerName = editTextBowlerName.getText().toString();
                if (!newBowlerName.isEmpty()) {
                    // TODO: Save the new bowler name to your database or do something with it
                    SharedPreferences database = getSharedPreferences("CricketDB", MODE_PRIVATE);
                    SharedPreferences.Editor edit = database.edit();
                    edit.putString("Bowler", newBowlerName);
                    edit.apply();

//                    Toast.makeText(ScoreCard.this, "New Bowler Name: " + newBowlerName, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(ScoreCard.this, "Please enter a valid bowler's name", Toast.LENGTH_SHORT).show();
                }
                initiliseBowler();
                printScore();
            }
        });

        // Handle the Cancel button click
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }





    private void initiliseBowler() {
        SharedPreferences sh = getSharedPreferences("CricketDB",MODE_PRIVATE);
        bolwername = sh.getString("Bowler","");
        b1 = new Bowler(bolwername);

        BolwerDataBaseHandler dbhandler = new BolwerDataBaseHandler(this);
        existingBowler = dbhandler.getBowlerData(bolwername);

    }


    public void InitialStart(){
        SharedPreferences sh = getSharedPreferences("CricketDB",MODE_PRIVATE);
        striker = sh.getString("Striker","");
        nonstriker = sh.getString("NonStriker","");
        bolwername = sh.getString("Bowler","");


        firstbatsman.setText(striker);
        secondbatsman.setText(nonstriker);
        bowler.setText(bolwername);

        txtScore.setText(total_run+"-"+wickets);


    }



    public  void printStar(){
        if(p1!=null && p2!=null) {
            if (striker.equals(p1.name)) {

                star1.setText("*");
            } else {
                star1.setText("");
            }

            if (striker.equals(p2.name)) {
                star2.setText("*");

            } else {
                star2.setText("");
            }
        }
    }

    public void afterWicketInitilisation() {
//        SharedPreferences sh = getSharedPreferences("CricketDB",MODE_PRIVATE);
//        String NewBtmn = sh.getString("newBatsman","");
//        if(out_player.equals(p1.name))
//        {
//            //save p1 object into sql
//            p1 = new Batsman(NewBtmn);
//            striker = p1.name;
//
//        }
//        else {
//            p2 = new Batsman(NewBtmn);
//            striker = p2.name;
//        }
//        SharedPreferences database = getSharedPreferences("CricketDB",MODE_PRIVATE);
//        SharedPreferences.Editor  edit = database.edit();
//        edit.putString("Striker",striker);
//        edit.commit();
//        Toast.makeText(getApplicationContext(), "New Batsman Initilisation into Database", Toast.LENGTH_SHORT).show();
//        firstbatsman.setText(p1.name);
//        secondbatsman.setText(p2.name);
        printScore();
        printStar();

//        InitialStart();
    }


}
